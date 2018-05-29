package com.bingo.joy.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.tool.AdsConstants;
import com.ads.tool.NetHelper;
import com.bingo.joy.R;
import com.bingo.joy.adapter.SortAdapter;
import com.bingo.joy.db.DBRiddleManager;
import com.bingo.joy.model.Riddle;
import com.bingo.joy.model.SortModel;
import com.bingo.joy.view.SideBar;
import com.bingo.joy.view.SideBar.OnTouchingLetterChangedListener;
import com.bingo.util.CharacterParser;
import com.bingo.util.MyDialog;
import com.bingo.util.PinyinComparator;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.open.GameAppOperation;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.tool.TecentConstants;
import com.tencent.tool.ThreadManager;
import com.tencent.tool.Util;
//import com.wandoujia.ads.sdk.Ads;
import com.wechat.tool.CameraUtil;
import com.wechat.tool.WeChatConstants;

public class MyRiddleActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static String Tag = MyRiddleActivity.class.getName();
    // BannerView bannerView;

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private String shareContent;
    private int riddleId;

    private int loveflag;
    private int flag;
    private int flag_Id;

    private TextView joy_title;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private ProgressDialog progressDialog;

    private static final int START = 0;

    private static final int OVER = 1;

    public static String mAppid;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    // private Boolean isTimelineCb;
    private static final int THUMB_SIZE = 250;
    private static final String SDCARD_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath();

    // 关于数据操作
    private DBRiddleManager dbRManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.love_activity);

        joy_title = (TextView) findViewById(R.id.joy_title);
        joy_title.setText("我的收藏——谜语");

        init();

//		showAd();

        // 从Intent 中获取数据
        Bundle buddle = this.getIntent().getExtras();
        loveflag = buddle.getInt("flag_showlove");
        flag = buddle.getInt("flag");
        flag_Id = buddle.getInt("flag_Id");
        System.out.println("==========flag_showlove=====" + loveflag);

        api = WXAPIFactory.createWXAPI(this, WeChatConstants.APP_ID, true);
        api.registerApp(WeChatConstants.APP_ID);

        mAppid = TecentConstants.APP_ID;
        TecentConstants.mTencent = Tencent.createInstance(mAppid, this);

        if (tips("")) {
            /* 开启一个新线程，在新线程里执行耗时的方法 */
            new Thread(new Runnable() {
                public void run() {
                    handler.sendEmptyMessage(0);
                    updateData("");
                    // 耗时的方法
                    handler.sendEmptyMessage(1);
                    // 执行耗时的方法之后发送消给handler
                }
            }).start();
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // handler接收到消息后就会执行此方法
            switch (msg.what) {
                case START:
                /* 显示ProgressDialog */
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    progressDialog = ProgressDialog.show(MyRiddleActivity.this,
                            "正在努力为您加载", "请稍后……");
                    break;
                case OVER:
                    if (adapter != null) {
                        sortListView.setAdapter(adapter);
                    }
                    progressDialog.dismiss();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

//	private void showAd() {
//		new AsyncTask<Void, Void, Boolean>() {
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				try {
//					Ads.init(MyRiddleActivity.this, AdsConstants.APP_ID,
//							AdsConstants.SECRET_KEY);
//					return true;
//				} catch (Exception e) {
//					Log.e("ads-sample", "error", e);
//					return false;
//				}
//			}
//
//			@Override
//			protected void onPostExecute(Boolean success) {
//				// 实例化LayoutParams(重要)
//				FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
//						FrameLayout.LayoutParams.MATCH_PARENT,
//						FrameLayout.LayoutParams.WRAP_CONTENT);
//				// 设置广告条的悬浮位置
//				layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER; // 这里示例为右下角
//
//				if (success) {
//					/**
//					 * pre load
//					 */
//					Ads.preLoad(AdsConstants.BANNER5, Ads.AdFormat.banner);
//
//					/**
//					 * add ad views
//					 */
//					View bannerView = Ads.createBannerView(
//							MyRiddleActivity.this, AdsConstants.BANNER5);
//
//					MyRiddleActivity.this.addContentView(bannerView,
//							layoutParams);
//				}
//			}
//		}.execute();
//	}
//
//	public void showAppWallAd(View v) {
//		new AsyncTask<Void, Void, Boolean>() {
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				try {
//					Ads.init(MyRiddleActivity.this, AdsConstants.APP_ID,
//							AdsConstants.SECRET_KEY);
//					return true;
//				} catch (Exception e) {
//					Log.e("ads-sample", "error", e);
//					return false;
//				}
//			}
//
//			@Override
//			protected void onPostExecute(Boolean success) {
//				/**
//				 * pre load
//				 */
//				Ads.preLoad(AdsConstants.APP_WALL, Ads.AdFormat.appwall);
//
//				if (NetHelper.IsHaveInternet(MyRiddleActivity.this)) {
//					Ads.showAppWall(MyRiddleActivity.this,
//							AdsConstants.APP_WALL);
//				} else {
//					Toast.makeText(MyRiddleActivity.this, "联网才能进入哦。",
//							Toast.LENGTH_SHORT).show();
//				}
//			}
//		}.execute();
//	}

    private void init() {
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);


        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView = (ListView) findViewById(R.id.mylove);

        sortListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(this, "想要更多操作请长按，谢谢合作。",
                // Toast.LENGTH_SHORT).show();
                String message = "长按可邀请好友猜谜哦。";
                MyDialog.myDialog(MyRiddleActivity.this, message);
            }
        });

        sortListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                final SortModel sm = SourceDateList.get(position);
                // riddleId = (int) id;

                // String share = sm.getName().substring(0,
                // sm.getName().indexOf(")"))
                // + ")";
                String share = sm.getName().toString().trim();
                if (!"".equals(share)) {
                    shareContent = share;
                } else {
                    shareContent = "";
                }

                showListDialog(newtan, sm);

                return true;
            }

            private String[] newtan = new String[]{"移除我的收藏", "邀请微信好友来猜",
                    "邀请QQ好友来猜", "分享到微信朋友圈", "分享到QQ空间", "发送至QQ（我的电脑）", "收藏到QQ",};

            private void showListDialog(final String[] arg, final SortModel sm) {
                new AlertDialog.Builder(MyRiddleActivity.this).setTitle("")
                        .setItems(arg, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    case 0:// 移除我的收藏
                                        if (GetRiddleId(sm.getName()) != -1) {
                                            onClickUnLove(shareContent);
                                            if (tips("")) {
                                            /* 开启一个新线程，在新线程里执行耗时的方法 */
                                                new Thread(new Runnable() {
                                                    public void run() {
                                                        handler.sendEmptyMessage(0);
                                                        updateData("");
                                                        // 耗时的方法
                                                        handler.sendEmptyMessage(1);
                                                        // 执行耗时的方法之后发送消给handler
                                                    }
                                                }).start();
                                            }

                                        } else {
                                            return;
                                        }
                                        break;
                                    case 1:// 邀请微信好友来猜
                                        sendTextToWeChat();
                                        break;
                                    case 2:// 邀请QQ好友来猜
                                        new Thread(new Runnable() {
                                            public void run() {
                                                handler.sendEmptyMessage(0);
                                                onClickShareToQQ();
                                                // 耗时的方法
                                                handler.sendEmptyMessage(1);
                                                // 执行耗时的方法之后发送消给handler
                                            }
                                        }).start();
                                        break;
                                    case 3:// 分享到微信朋友圈
                                        new Thread(new Runnable() {
                                            public void run() {
                                                handler.sendEmptyMessage(0);
                                                sendTextToWeChatZone();
                                                // 耗时的方法
                                                handler.sendEmptyMessage(1);
                                                // 执行耗时的方法之后发送消给handler
                                            }
                                        }).start();
                                        break;
                                    case 4:// 分享到QQ空间
                                        new Thread(new Runnable() {
                                            public void run() {
                                                handler.sendEmptyMessage(0);
                                                shareToQzone();
                                                // 耗时的方法
                                                handler.sendEmptyMessage(1);
                                                // 执行耗时的方法之后发送消给handler
                                            }
                                        }).start();
                                        break;
                                    case 5:// 发送至QQ（我的电脑）
                                        new Thread(new Runnable() {
                                            public void run() {
                                                handler.sendEmptyMessage(0);
                                                onClickShareTextToComputer();
                                                // 耗时的方法
                                                handler.sendEmptyMessage(1);
                                                // 执行耗时的方法之后发送消给handler
                                            }
                                        }).start();
                                        break;
                                    case 6:// 收藏到QQ
                                        new Thread(new Runnable() {
                                            public void run() {
                                                handler.sendEmptyMessage(0);
                                                onClickLoveText();
                                                // 耗时的方法
                                                handler.sendEmptyMessage(1);
                                                // 执行耗时的方法之后发送消给handler
                                            }
                                        }).start();

                                        break;

                                }
                                ;
                            }
                        }).show();
            }
        });

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    // 移除本地收藏
    private void onClickUnLove(String riddleDes) {
        dbRManager = new DBRiddleManager(this);
        Riddle riddle = new Riddle();

        riddle.setRiddleId(GetRiddleId(riddleDes));
        riddle.setRiddleRemark("无");
        String message;
        if (dbRManager.update(riddle)) {
            message = "移除收藏夹成功！";
        } else {
            message = "移除收藏夹失败！";
        }
        MyDialog.myDialog(this, message);
        updateData("");
    }

    // 根据riddleDes查找RiddleId
    private int GetRiddleId(String riddleDes) {
        dbRManager = new DBRiddleManager(this);
        List<Riddle> list = new ArrayList<Riddle>();
        list = dbRManager.findRiddleIdByDes(riddleDes);

        if (list != null && list.size() > 0) {
            System.out.println("=====GetRiddleId======list.size()"
                    + list.size());
            riddleId = list.get(0).getRiddleId();
            System.out.println("======GetRiddleId====riddleId" + riddleId);
            return riddleId;
        } else {
            return -1;
        }

    }

    private boolean tips(String kind) {
        dbRManager = new DBRiddleManager(MyRiddleActivity.this);
        List<Riddle> list = new ArrayList<Riddle>();
        list = dbRManager.findRiddlesByRemark(kind);

        if (list == null) {
            return false;
        } else {
            return true;
        }

    }

    private String[] getRiddlesByKind(String kind) {
        dbRManager = new DBRiddleManager(this);
        List<Riddle> list = new ArrayList<Riddle>();
        list = dbRManager.findRiddlesByRemark(kind);

        if (list != null) {
            System.out.println("===========list.size()" + list.size());
            int size = list.size();
            String[] str = new String[size];
            for (int i = 0; i < size; i++) {
                str[i] = list.get(i).getRiddleDes().trim() + "—— 谜底："
                        + list.get(i).getRiddleKey().trim();
            }
            return (str);
        } else {
            // Toast.makeText(this, "没有获取到谜语。", Toast.LENGTH_SHORT)
            // .show();
            String[] str = new String[1];
            str[0] = "收藏夹为空！";
            // MyDialog.myDialog(MyRiddleActivity.this, message);
            return str;
        }

    }

    private String[] getRiddleDesByKind(String kind) {
        dbRManager = new DBRiddleManager(this);
        List<Riddle> list = new ArrayList<Riddle>();
        list = dbRManager.findRiddlesByRemark(kind);

        if (list.size() > 0) {
            System.out.println("===========list.size()" + list.size());
            int size = list.size();
            String[] str = new String[size];
            for (int i = 0; i < size; i++) {
                str[i] = list.get(i).getRiddleDes().trim();
            }
            return (str);
        } else {
            // Toast.makeText(this, "没有获取到谜语。", Toast.LENGTH_SHORT)
            // .show();
            String[] str = new String[1];
            str[0] = "收藏夹为空！";
            // MyDialog.myDialog(MyRiddleActivity.this, message);
            return str;
        }
    }

    private String[] getRiddleKeyByKind(String kind) {
        dbRManager = new DBRiddleManager(this);
        List<Riddle> list = new ArrayList<Riddle>();
        list = dbRManager.findRiddlesByRemark(kind);

        if (list.size() > 0) {
            System.out.println("===========list.size()" + list.size());
            int size = list.size();
            String[] str = new String[size];
            for (int i = 0; i < size; i++) {
                str[i] = "—— 谜底：" + list.get(i).getRiddleKey().trim();
            }
            return (str);
        } else {
            // Toast.makeText(this, "没有获取到谜语。", Toast.LENGTH_SHORT)
            // .show();
            String[] str = new String[1];
            str[0] = "收藏夹为空！";
            // MyDialog.myDialog(MyRiddleActivity.this, message);
            return str;
        }
    }

    private void updateData(String kind) {
        // 实例化汉字转拼音类
        SourceDateList = filledData(getRiddlesByKind(kind),
                getRiddleDesByKind(kind), getRiddleKeyByKind(kind));
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
    }

    /**
     * 为ListView填充数据
     *
     * @param data, riddleDes
     * @return
     */
    private List<SortModel> filledData(String[] data, String[] riddleDes,
                                       String[] riddleKey) {
        List<SortModel> mSortList = new ArrayList<SortModel>();
        for (int i = 0; i < riddleDes.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setData(data[i]);
            sortModel.setName(riddleDes[i]);
            sortModel.setKey(riddleKey[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(riddleDes[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    // 发送文本到微信
    private void sendTextToWeChat() {
        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = shareContent;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        msg.title = "Will be ignored";
        msg.description = shareContent;

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        // req.scene = isTimelineCb? SendMessageToWX.Req.WXSceneTimeline :
        // SendMessageToWX.Req.WXSceneSession;;

        // 调用api接口发送数据到微信
        api.sendReq(req);
        // this.finish();
    }

    // 发送文本到微信朋友圈
    private void sendTextToWeChatZone() {
        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = shareContent;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        msg.title = "Will be ignored";
        msg.description = shareContent;

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        // 调用api接口发送数据到微信
        api.sendReq(req);
        // this.finish();
    }

    // 分享到我的电脑文本信息
    private void onClickShareTextToComputer() {
        final Bundle params = new Bundle();
        params.putString(GameAppOperation.QQFAV_DATALINE_APPNAME, "一起玩儿");
        // params.putString(GameAppOperation.QQFAV_DATALINE_TITLE,"分享");
        params.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE,
                GameAppOperation.QQFAV_DATALINE_TYPE_TEXT);
        params.putString(GameAppOperation.QQFAV_DATALINE_DESCRIPTION,
                shareContent);
        TecentConstants.mTencent.sendToMyComputer(this, params,
                qqtestShareListener);
    }

    // 收藏到我的QQ
    private void onClickLoveText() {
        final Bundle params = new Bundle();
        params.putString(GameAppOperation.QQFAV_DATALINE_APPNAME, "一起玩儿");
        // params.putString(GameAppOperation.QQFAV_DATALINE_TITLE,
        // title.getText().toString());
        params.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE,
                GameAppOperation.QQFAV_DATALINE_TYPE_TEXT);
        params.putString(GameAppOperation.QQFAV_DATALINE_DESCRIPTION,
                shareContent);
        TecentConstants.mTencent.addToQQFavorites(this, params,
                qqtestShareListener);
    }

    // 分享到QQ
    private void onClickShareToQQ() {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                final Bundle params = new Bundle();
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
                        QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "猜一猜");
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareContent);
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                        TecentConstants.shareTargetUrl);
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                        "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "一起玩儿");
                TecentConstants.mTencent.shareToQQ(MyRiddleActivity.this,
                        params, qqtestShareListener);
            }
        });
    }

    // 分享到QQ空间
    private void shareToQzone() {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                final Bundle params = new Bundle();
                params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "猜一猜");// 必填
                params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareContent);
                params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,
                        TecentConstants.shareTargetUrl);// 必填
                // 支持传多个imageUrl
                ArrayList<String> imageUrls = new ArrayList<String>();
                for (int i = 0; i < 1; i++) {
                    imageUrls
                            .add("http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
                }
                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,
                        imageUrls);
                TecentConstants.mTencent.shareToQzone(MyRiddleActivity.this,
                        params, qqtestShareListener);
            }
        });
    }

    IUiListener qqtestShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            Log.d("MyRiddleActivity", "-----onCancel()------");
        }

        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            Util.toastMessage(MyRiddleActivity.this,
                    "onComplete: " + response.toString());
            Toast.makeText(MyRiddleActivity.this,
                    "result=" + response.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            Util.toastMessage(MyRiddleActivity.this, "onError: "
                    + e.errorMessage, "e");
        }
    };

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "requestCode:" + requestCode, Toast.LENGTH_SHORT)
                .show();
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("===========requestCode:" + requestCode);
        switch (requestCode) {

            case 0x101: {
                final WXAppExtendObject appdata = new WXAppExtendObject();
                final String path = CameraUtil.getResultPhotoPath(this, data,
                        SDCARD_ROOT + "/tencent/");
                appdata.filePath = path;
                appdata.extInfo = "this is ext info";

                final WXMediaMessage msg = new WXMediaMessage();
                msg.setThumbImage(Util.extractThumbNail(path, 150, 150, true));
                msg.title = "this is title";
                msg.description = "this is description";
                msg.mediaObject = appdata;

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("appdata");
                req.message = msg;
                // req.scene = isTimelineCb ? SendMessageToWX.Req.WXSceneTimeline :
                // SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);

                break;
            }
            default:
                break;
        }
    }

    public void openActivity_Main(View v) {
        Intent intent;
        if (loveflag == 1) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, RiddleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("flag_showlove", 2);
            bundle.putInt("flag", flag);
            bundle.putInt("flag_Id", flag_Id);
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
        this.finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent;
            if (loveflag == 1) {
                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, RiddleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flag_showlove", 2);
                bundle.putInt("flag", flag);
                bundle.putInt("flag_Id", flag_Id);
                intent.putExtras(bundle);
            }
            startActivity(intent);
            this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
            this.finish();
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        } // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
