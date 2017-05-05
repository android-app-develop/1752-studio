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
import com.bingo.joy.db.DBRiddleManager;
import com.bingo.joy.model.Riddle;
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

public class RiddleActivity extends Activity {

	/** Called when the activity is first created. */
	public static String Tag = RiddleActivity.class.getName();
//	BannerView bannerView;

	private TextView riddleKey;

	private TextView title;
	private TextView previous;
	private TextView next;
	private ImageView share;
	private ImageView like;

	private TextView riddleDes;
	private int riddleId;
	private String remark;

	private EditText guessKey;
	private Button guess;

	// private int i;// 随机id
	private int j;// 猜的次数
	private int firstId;// 获取到的firstId
	private int lastId;// 获取到的lastId
	private String kind = "";
	private int flag;
	private int flag_love;
	private int flag_Id;

	private String shareContent;

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.riddle_activity);

		init();

//		showAd();

		dbRManager = new DBRiddleManager(this);

		// 从Intent 中获取数据
		Bundle buddle = this.getIntent().getExtras();
		flag = buddle.getInt("flag");
		flag_Id = buddle.getInt("flag_Id");
		System.out.println("==========flag=====" + flag);
		System.out.println("==========flag_Id=====" + flag_Id);

		switch (flag) {
		case 1:
			kind = "经典";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——经典");
			break;
		case 2:
			kind = "动物";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——动物");
			break;
		case 3:
			kind = "爱情";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——爱情");
			break;
		case 4:
			kind = "搞笑";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——搞笑");
			break;
		case 5:
			kind = "儿童";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——儿童");
			break;
		case 6:
			kind = "字谜";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——字谜");
			break;
		case 7:
			kind = "英语";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = firstId;
			} else {
				riddleId = flag_Id;
			}
			title.setText("谜语——英语");
			break;
		default:
			kind = "";
			getRiddleFirstIdByKind(kind);
			if (flag_Id == -1) {
				riddleId = RandUtil.getRandom(firstId, lastId); // 生成firstId-lastId以内的随机数
			} else {
				riddleId = flag_Id;
			}

			title.setText("谜语——随机");
			break;
		}

		changeLikeState();

		getRiddleDesById(riddleId);

		System.out.println("===hh==i======" + riddleId);
		System.out.println("===hh==firstId======" + firstId);

		api = WXAPIFactory.createWXAPI(this, WeChatConstants.APP_ID, true);
		api.registerApp(WeChatConstants.APP_ID);

		mAppid = TecentConstants.APP_ID;
		TecentConstants.mTencent = Tencent.createInstance(mAppid, this);
	}

	public void init() {
		title = (TextView) findViewById(R.id.riddle_title);
		riddleDes = (TextView) findViewById(R.id.riddleDes);
		riddleKey = (TextView) findViewById(R.id.riddleKey);
		previous = (TextView) findViewById(R.id.previous);
		next = (TextView) findViewById(R.id.next);
		share = (ImageView) findViewById(R.id.riddle_share);
		like = (ImageView) findViewById(R.id.riddle_love);

		guessKey = (EditText) findViewById(R.id.guessKey);
		guess = (Button) findViewById(R.id.guess);
	}

//	private void showAd() {
//		new AsyncTask<Void, Void, Boolean>() {
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				try {
//					Ads.init(RiddleActivity.this, AdsConstants.APP_ID,
//							AdsConstants.SECRET_KEY);
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
//					Ads.preLoad(AdsConstants.BANNER2, Ads.AdFormat.banner);
//
//					/**
//					 * add ad views
//					 */
//					View bannerView = Ads.createBannerView(RiddleActivity.this,
//							AdsConstants.BANNER2);
//
//					RiddleActivity.this.addContentView(bannerView, layoutParams);
//				}
//			}
//		}.execute();
//	}

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
											RiddleActivity.this.CLIPBOARD_SERVICE);
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
				TecentConstants.mTencent.shareToQQ(RiddleActivity.this, params,
						qqtestShareListener);
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
				TecentConstants.mTencent.shareToQzone(RiddleActivity.this,
						params, qqtestShareListener);
			}
		});
	}

	private void getRiddleDesById(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();
		riddle = dbRManager.findRiddleById(riddleId);
		if (riddle != null) {
			riddleDes.setText(riddle.getRiddleDes().trim());
		} else {
			riddleDes.setText("很遗憾，没有获取到谜语。");
		}
	}

	private void getRiddleFirstIdByKind(String kind) {
		dbRManager = new DBRiddleManager(this);
		List<Riddle> list = new ArrayList<Riddle>();
		list = dbRManager.findRiddlesByKind(kind);

		if (list.size() > 0) {
			System.out.println("===========list.size()" + list.size());
			firstId = list.get(0).getRiddleId();
			lastId = list.get(list.size() - 1).getRiddleId();
		} else {
			String message = "很遗憾，没有获取到谜语。";
			MyDialog.myDialog(this, message);
		}
	}

	private void getRiddleKeyById(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();
		riddle = dbRManager.findRiddleById(riddleId);

		if (riddle != null) {
			System.out.println("=====guessKey.getText()===="
					+ guessKey.getText().toString().trim());
			System.out.println("=====riddle.getRiddleKey()===="
					+ riddle.getRiddleKey());
			if (guessKey.getText().toString().trim()
					.equals(riddle.getRiddleKey().toString().trim())) {
				String message = "真厉害！恭喜你猜对了。";
				MyDialog.myDialog(this, message);

			} else {
				j++;
				String message = "很遗憾，你猜错了，再猜猜。";
				MyDialog.myDialog(this, message);
			}
		} else {
			String message = "很遗憾，没有获取到谜底。";
			MyDialog.myDialog(this, message);
		}
	}

	private void setRiddleKey(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();
		riddle = dbRManager.findRiddleById(riddleId);
		if (riddle != null) {
			riddleKey.setText(riddle.getRiddleKey());
		} else {
			Toast.makeText(this, "没有获取到谜底。", Toast.LENGTH_SHORT).show();
		}
	}

	private void isLove() {
		if (flag_love == 1) {
			onClickLove(riddleId);
		} else {
			onClickUnLove(riddleId);
		}
	}

	// 收藏到本地
	private void onClickLove(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();
		riddle.setRiddleId(riddleId);
		riddle.setRiddleRemark("最爱");
		String message;
		if (dbRManager.update(riddle)) {
			message = "收藏成功！";
			changeLikeState();
		} else {
			message = "收藏失败！";
		}
		MyDialog.myDialog(this, message);
	}

	// 移除本地收藏
	private void onClickUnLove(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();

		riddle.setRiddleId(riddleId);
		riddle.setRiddleRemark("无");
		String message;
		if (dbRManager.update(riddle)) {
			message = "移除收藏夹成功！";
			changeLikeState();
		} else {
			message = "移除收藏夹失败！";
		}
		MyDialog.myDialog(this, message);

	}

	private String getRemark(int riddleId) {
		dbRManager = new DBRiddleManager(this);
		Riddle riddle = new Riddle();
		riddle = dbRManager.findRiddleById(riddleId);
		if (riddle != null) {
			remark = riddle.getRiddleRemark().trim();
		} else {
			remark = "";
		}

		return remark;
	}

	private void changeLikeState() {
		if ("最爱".equals(getRemark(riddleId))) {
			like.setImageResource(R.drawable.unlike);
			flag_love = 0;
		} else {
			like.setImageResource(R.drawable.like);
			flag_love = 1;
		}
	}

	IUiListener qqtestShareListener = new IUiListener() {
		@Override
		public void onCancel() {
			Util.toastMessage(RiddleActivity.this, "onCancel: ");
		}

		@Override
		public void onComplete(Object response) {
			// TODO Auto-generated method stub
			Util.toastMessage(RiddleActivity.this,
					"onComplete: " + response.toString());
			Toast.makeText(RiddleActivity.this,
					"result=" + response.toString(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(UiError e) {
			// TODO Auto-generated method stub
			Util.toastMessage(RiddleActivity.this,
					"onError: " + e.errorMessage, "e");
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
//		v.startAnimation(shake);
		shareContent = riddleDes.getText().toString().trim();
		showListDialog(newtan);
	}

	public void guess(View v) {

		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			if (!"".equals(guessKey.getText().toString().trim())) {
				getRiddleKeyById(riddleId);
			} else {
				v.startAnimation(shake);
				String message = "谜底不能为空哦！";
				MyDialog.myDialog(RiddleActivity.this, message);
			}
		}
	}

	public void previous(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			guessKey.setText(null);
			riddleKey.setText(null);
			j = 0;
			riddleId--;
			if (riddleId >= firstId) {
				getRiddleDesById(riddleId);
				changeLikeState();
			} else {
				v.startAnimation(shake);
				String message = "已经是第一条数据了。";
				MyDialog.myDialog(RiddleActivity.this, message);
			}
		}
	}

	public void next(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			guessKey.setText(null);
			riddleKey.setText(null);
			j = 0;
			if (riddleId < lastId) {
				riddleId++;
				getRiddleDesById(riddleId);
				changeLikeState();
			} else {
				v.startAnimation(shake);
				String message = "已经是最后一条数据了。";
				MyDialog.myDialog(RiddleActivity.this, message);
			}
		}
	}

	public void key(View v) {
		Context context = v.getContext();
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);

		if (!FastClickUtil.isFastClick()) {
//			v.startAnimation(shake);
			setRiddleKey(riddleId);
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

	public void showLoveRiddle(View v) {
		Intent intent = new Intent(this, MyRiddleActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("flag_showlove", 2);
		bundle.putInt("flag", flag);
		bundle.putInt("flag_Id", riddleId);
		intent.putExtras(bundle);
		startActivity(intent);
		this.overridePendingTransition(R.anim.enteralpha, R.anim.outalpha);// 实现Activity切换动画效果
		this.finish();
	}

}
