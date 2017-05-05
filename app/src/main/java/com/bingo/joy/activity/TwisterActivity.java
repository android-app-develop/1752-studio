package com.bingo.joy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.tool.AdsConstants;
import com.bingo.joy.R;
import com.bingo.joy.db.DBTwisterManager;
import com.bingo.joy.model.Twister;
import com.bingo.util.FastClickUtil;
import com.bingo.util.MyDialog;
import com.bingo.util.RandUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.tool.TecentConstants;
import com.tencent.tool.ThreadManager;
import com.tencent.tool.Util;
//import com.wandoujia.ads.sdk.Ads;
import com.wechat.tool.WeChatConstants;

public class TwisterActivity extends Activity{
	/** Called when the activity is first created. */
	public static String Tag = TwisterActivity.class.getName();
//	BannerView bannerView;

	private TextView twisterKey;

	private TextView title;
	private TextView previous;
	private TextView next;

	private ImageView share;
	private ImageView like;

	private TextView twisterDes;

	private EditText guessKey;
	private Button guess;

	private int i;// 随机脑筋急转弯id
	private int j;// 猜的次数
	private int firstId;// 获取到的灯谜的条数
	private int lastId;// 获取到的灯谜的条数
	private String kind = "";

	private String remark;

	private int flag;
	private int flag_love;
	private int flag_Id;
	// private Boolean alert = true;

	private String shareContent;

	public static String mAppid;

	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;
	// private Boolean isTimelineCb;
	private static final int THUMB_SIZE = 250;
	private static final String SDCARD_ROOT = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 关于数据操作
	private DBTwisterManager dbTManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twister_activity);

		init();
//		showAd();

		dbTManager = new DBTwisterManager(this);

		// 从Intent 中获取数据
		Bundle buddle = this.getIntent().getExtras();
		flag = buddle.getInt("flag");
		flag_Id = buddle.getInt("flag_Id");
		System.out.println("==========flag=====" + flag);
		System.out.println("==========flag_Id=====" + flag_Id);

		switch (flag) {
		case 11:
			kind = "经典";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——经典");
			break;
		case 12:
			kind = "动物";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——动物");
			break;
		case 13:
			kind = "益智";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——益智");
			break;
		case 14:
			kind = "搞笑";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——搞笑");
			break;
		case 15:
			kind = "儿童";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——儿童");
			break;
		case 16:
			kind = "冷笑话";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——冷笑话");
			break;
		case 17:
			kind = "很难";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = firstId;
			} else {
				i = flag_Id;
			}
			title.setText("脑筋急转弯——超难");
			break;
		default:
			kind = "";
			getTwisterFirstIdByKind(kind);
			if (flag_Id == -1) {
				i = RandUtil.getRandom(firstId, lastId); // 生成firstId-lastId以内的随机数
			} else {
				i = flag_Id;
			}

			title.setText("脑筋急转弯——随机");
			break;
		}

		getTwisterDesById(i);

		changeLikeState();

		api = WXAPIFactory.createWXAPI(this, WeChatConstants.APP_ID, true);
		api.registerApp(WeChatConstants.APP_ID);

		mAppid = TecentConstants.APP_ID;
		TecentConstants.mTencent = Tencent.createInstance(mAppid, this);
	}

	public void init() {
		title = (TextView) findViewById(R.id.twister_title);
		twisterDes = (TextView) findViewById(R.id.twisterDes);
		twisterKey = (TextView) findViewById(R.id.twisterKey);
		previous = (TextView) findViewById(R.id.previous);
		next = (TextView) findViewById(R.id.next);

		share = (ImageView) findViewById(R.id.twister_share);
		like = (ImageView) findViewById(R.id.twister_love);

		guessKey = (EditText) findViewById(R.id.guessKey);
		guess = (Button) findViewById(R.id.guess);
	}

//	private void showAd() {
//		new AsyncTask<Void, Void, Boolean>() {
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				try {
////					Ads.init(TwisterActivity.this, AdsConstants.APP_ID,
////							AdsConstants.SECRET_KEY);
//					return true;
//				} catch (Exception e) {
//					Log.e("ads-sample", "error", e);
//					return false;
//				}
//			}

//			@Override
//			protected void onPostExecute(Boolean success) {
//				// 实例化LayoutParams(重要)
//				FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
//						FrameLayout.LayoutParams.MATCH_PARENT,
//						FrameLayout.LayoutParams.WRAP_CONTENT);
//				// 设置广告条的悬浮位置
//				layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER; // 这里示例为右下角

//				if (success) {
//					/**
//					 * pre load
//					 */
//					Ads.preLoad(AdsConstants.BANNER4, Ads.AdFormat.banner);
//
//					/**
//					 * add ad views
//					 */
//					View bannerView = Ads.createBannerView(TwisterActivity.this,
//							AdsConstants.BANNER4);
//
//					TwisterActivity.this.addContentView(bannerView, layoutParams);
//				}
//			}
//		}.execute();
//	}

	private void getTwisterDesById(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();
		twister = dbTManager.findTwisterById(twisterId);
		if (twister != null) {
			twisterDes.setText(twister.getTwisterDes().trim());
		} else {
			twisterDes.setText("很遗憾，没有获取到脑筋急转弯。");
		}
	}

	private void getTwisterFirstIdByKind(String kind) {
		dbTManager = new DBTwisterManager(this);
		List<Twister> list = new ArrayList<Twister>();
		list = dbTManager.findTwistersByKind(kind);

		if (list.size() > 0) {
			System.out.println("===========list.size()" + list.size());
			firstId = list.get(0).getTwisterId();
			lastId = list.get(list.size() - 1).getTwisterId();
		} else {
			String message = "很遗憾，没有获取到脑筋急转弯。";
			MyDialog.myDialog(this, message);
		}
	}

	private void getTwisterKeyById(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();
		twister = dbTManager.findTwisterById(twisterId);

		if (twister != null) {
			System.out.println("=====guessKey.getText()===="
					+ guessKey.getText().toString().trim());
			System.out.println("=====twister.getTwisterKey()===="
					+ twister.getTwisterKey());
			if (guessKey.getText().toString().trim()
					.equals(twister.getTwisterKey().toString().trim())) {
				String message = "真厉害！恭喜你猜对了。";
				MyDialog.myDialog(this, message);

			} else {
				j++;
				String message = "很遗憾，你猜错了，再猜猜。";
				MyDialog.myDialog(this, message);
			}
		} else {
			String message = "很遗憾，没有获取到答案。";
			MyDialog.myDialog(this, message);
		}
	}

	private void setTwisterKey(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();
		twister = dbTManager.findTwisterById(twisterId);
		if (twister != null) {
			twisterKey.setText(twister.getTwisterKey());
		} else {
			Toast.makeText(this, "没有获取到答案。", Toast.LENGTH_SHORT).show();
		}
	}

	private String[] newtan = new String[] { "收藏", "复制", "邀请微信好友来猜",
			"邀请QQ好友来猜", "分享到微信朋友圈", "分享到QQ空间" };

	private void showListDialog(final String[] arg) {
		new AlertDialog.Builder(this).setTitle("")
				.setItems(arg, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {

						case 0:// 收藏
							isLove();
							break;

						case 1:// 复制
							ClipboardManager cmb = (ClipboardManager) getApplicationContext()
									.getSystemService(
											TwisterActivity.this.CLIPBOARD_SERVICE);
							cmb.setText(shareContent);
							Toast.makeText(getApplicationContext(), "复制成功！",
									Toast.LENGTH_SHORT).show();
							break;
						case 2:// 邀请微信好友来猜
							sendTextToWeChat();
							break;
						case 3:// 邀请QQ好友来猜
							new Thread(new Runnable() {
								public void run() {
									onClickShareToQQ();
									// 耗时的方法
								}
							}).start();
							break;
						case 4:// 分享到微信朋友圈
							new Thread(new Runnable() {
								public void run() {
									sendTextToWeChatZone();
									// 耗时的方法
								}
							}).start();
							break;
						case 5:// 分享到QQ空间
							new Thread(new Runnable() {
								public void run() {
									shareToQzone();
									// 耗时的方法
								}
							}).start();
							break;
						}
						;
					}
				}).show();
	}

	private void isLove() {
		if (flag_love == 1) {
			onClickLove(i);
		} else {
			onClickUnLove(i);
		}
	}

	// 收藏到本地
	private void onClickLove(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();
		twister.setTwisterId(twisterId);
		twister.setTwisterRemark("最爱");
		String message;
		if (dbTManager.update(twister)) {
			message = "收藏成功！";
			changeLikeState();
		} else {
			message = "收藏失败！";
		}
		MyDialog.myDialog(this, message);
	}

	// 移除本地收藏
	private void onClickUnLove(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();

		twister.setTwisterId(twisterId);
		twister.setTwisterRemark("无");
		String message;
		if (dbTManager.update(twister)) {
			message = "移除收藏夹成功！";
			changeLikeState();
		} else {
			message = "移除收藏夹失败！";
		}
		MyDialog.myDialog(this, message);

	}

	private String getRemark(int twisterId) {
		dbTManager = new DBTwisterManager(this);
		Twister twister = new Twister();
		twister = dbTManager.findTwisterById(twisterId);
		if (twister != null) {
			remark = twister.getTwisterRemark().trim();
		} else {
			remark = "";
		}

		return remark;
	}

	private void changeLikeState() {
		if ("最爱".equals(getRemark(i))) {
			like.setImageResource(R.drawable.unlike);
			flag_love = 0;
		} else {
			like.setImageResource(R.drawable.like);
			flag_love = 1;
		}
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
				TecentConstants.mTencent.shareToQQ(TwisterActivity.this,
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
				TecentConstants.mTencent.shareToQzone(TwisterActivity.this,
						params, qqtestShareListener);
			}
		});
	}

	IUiListener qqtestShareListener = new IUiListener() {
		@Override
		public void onCancel() {
			Util.toastMessage(TwisterActivity.this, "onCancel: ");
		}

		@Override
		public void onComplete(Object response) {
			// TODO Auto-generated method stub
			Util.toastMessage(TwisterActivity.this,
					"onComplete: " + response.toString());
			Toast.makeText(TwisterActivity.this,
					"result=" + response.toString(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(UiError e) {
			// TODO Auto-generated method stub
			Util.toastMessage(TwisterActivity.this, "onError: "
					+ e.errorMessage, "e");
		}
	};

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
			this.finish();
		}
		return false;
	}

	public void openActivity_Main(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
		this.finish();
	}

	public void share(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			shareContent = twisterDes.getText().toString().trim()+"。 (猜一猜)";
			showListDialog(newtan);
		}
	}

	public void guess(View v) {

		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			if (!"".equals(guessKey.getText().toString().trim())) {
				getTwisterKeyById(i);
			} else {
				v.startAnimation(shake);
				String message = "答案不能为空哦！";
				MyDialog.myDialog(TwisterActivity.this, message);
			}
		}
	}

	public void previous(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			guessKey.setText(null);
			twisterKey.setText(null);
			j = 0;
			if (i > firstId) {
				i--;
				getTwisterDesById(i);
				changeLikeState();
			} else {
				v.startAnimation(shake);
				String message = "已经是第一条数据了。";
				MyDialog.myDialog(TwisterActivity.this, message);
			}
		}
	}

	public void next(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			guessKey.setText(null);
			twisterKey.setText(null);
			j = 0;
			if (i < lastId) {
				i++;
				getTwisterDesById(i);
				changeLikeState();
			} else {
				v.startAnimation(shake);
				String message = "已经是最后一条数据了。";
				MyDialog.myDialog(TwisterActivity.this, message);
			}
		}
	}

	public void key(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			setTwisterKey(i);
		}
	}

	public void love(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			isLove();
		}
	}

	public void showLoveTwister(View v) {
		Intent intent = new Intent(this, MyTwisterActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("flag_showlove", 2);
		bundle.putInt("flag", flag);
		bundle.putInt("flag_Id", i);
		intent.putExtras(bundle);
		startActivity(intent);
		this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
		this.finish();
	}

}
