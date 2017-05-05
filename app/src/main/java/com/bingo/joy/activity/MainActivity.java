package com.bingo.joy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.domob.android.ads.Updater;

import com.adjumi.demo.push.ut.AdManager;
import com.ads.tool.AdsConstants;
import com.bingo.joy.R;
import com.bingo.util.Preference;
//import com.wandoujia.ads.sdk.Ads;
import com.ps.test.Manager;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	public static String Tag = RiddleActivity.class.getName();

	private TableLayout main_background;
	private ImageView styleSrc;

	int i = 0;

	Manager manager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		init();

		initRiddle();
		initTwister();
		initSaying();

		reminder();
//		showPushAd();
//		showBannerAd();
		initPop();
	}

	public void init() {
		styleSrc = (ImageView) findViewById(R.id.main_style);
		main_background = (TableLayout) findViewById(R.id.main_background);

	}

	private void reminder() {
		new Thread(new Runnable() {
			public void run() {
				Updater.checkUpdate(MainActivity.this, "56OJ2tEouNwgLiceMK");
			}
		}).start();
	}

//	private void showPushAd() {
//		new Thread(new Runnable() {
//			public void run() {
//				AdManager m = AdManager.getInstance(MainActivity.this, 1,
//						"98966546-5f44-430e-a03f-3e74286694dc", 6);
//				m.set(null, true, true);
//				m.start();
//			}
//		}).start();
//	}

	private void initPop() {
		/* 开启一个新线程，在新线程里执行耗时的方法 */
		new Thread(new Runnable() {
			public void run() {
				manager = Manager.getInstance(getApplicationContext(),
						"98966546-5f44-430e-a03f-3e74286694dc", 1);
				// 配置插屏
				manager.c(6, 6, false);
				// 配置外插屏
				// manager.o(true, false, 20, false, true, true);
				// 耗时的方法
			}
		}).start();

	}

	public void showPop(View v) {
		/* 开启一个新线程，在新线程里执行耗时的方法 */
		new Thread(new Runnable() {
			public void run() {
				manager.s(MainActivity.this);
				// 耗时的方法
			}
		}).start();
	}

//	private void showBannerAd() {
//		new AsyncTask<Void, Void, Boolean>() {
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				try {
//					Ads.init(MainActivity.this, AdsConstants.APP_ID,
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
//					Ads.preLoad(AdsConstants.BANNER1, Ads.AdFormat.banner);
//
//					/**
//					 * add ad views
//					 */
//					View bannerView = Ads.createBannerView(MainActivity.this,
//							AdsConstants.BANNER1);
//
//					MainActivity.this.addContentView(bannerView, layoutParams);
//				}
//			}
//		}.execute();
//	}

	public void initRiddle() {
		TextView riddle_classic;
		TextView riddle_animal;
		TextView riddle_love;
		TextView riddle_funny;
		TextView riddle_child;
		TextView riddle_word;
		TextView riddle_english;
		TextView riddle_test1;
		TextView riddle_test2;
		riddle_classic = (TextView) findViewById(R.id.activity_main_tv_riddle_classic);
		riddle_animal = (TextView) findViewById(R.id.activity_main_tv_riddle_animal);
		riddle_love = (TextView) findViewById(R.id.activity_main_tv_riddle_love);
		riddle_funny = (TextView) findViewById(R.id.activity_main_tv_riddle_funny);
		riddle_child = (TextView) findViewById(R.id.activity_main_tv_riddle_child);
		riddle_word = (TextView) findViewById(R.id.activity_main_tv_riddle_word);
		riddle_english = (TextView) findViewById(R.id.activity_main_tv_riddle_english);
		riddle_test1 = (TextView) findViewById(R.id.activity_main_tv_riddle_8);
		riddle_test2 = (TextView) findViewById(R.id.activity_main_tv_riddle_9);
		riddle_classic.setOnClickListener(new riddleOnClick());
		riddle_animal.setOnClickListener(new riddleOnClick());
		riddle_love.setOnClickListener(new riddleOnClick());
		riddle_funny.setOnClickListener(new riddleOnClick());
		riddle_child.setOnClickListener(new riddleOnClick());
		riddle_word.setOnClickListener(new riddleOnClick());
		riddle_english.setOnClickListener(new riddleOnClick());
		riddle_test1.setOnClickListener(new riddleOnClick());
		riddle_test2.setOnClickListener(new riddleOnClick());
	}

	public void initTwister() {
		TextView twister_classic;
		TextView twister_animal;
		TextView twister_love;
		TextView twister_funny;
		TextView twister_child;
		TextView twister_word;
		TextView twister_english;
		TextView twister_test1;
		TextView twister_test2;
		twister_classic = (TextView) findViewById(R.id.activity_main_tv_twister_1);
		twister_animal = (TextView) findViewById(R.id.activity_main_tv_twister_2);
		twister_love = (TextView) findViewById(R.id.activity_main_tv_twister_3);
		twister_funny = (TextView) findViewById(R.id.activity_main_tv_twister_4);
		twister_child = (TextView) findViewById(R.id.activity_main_tv_twister_5);
		twister_word = (TextView) findViewById(R.id.activity_main_tv_twister_6);
		twister_english = (TextView) findViewById(R.id.activity_main_tv_twister_7);
		twister_test1 = (TextView) findViewById(R.id.activity_main_tv_twister_8);
		twister_test2 = (TextView) findViewById(R.id.activity_main_tv_twister_9);
		twister_classic.setOnClickListener(new twisterOnClick());
		twister_animal.setOnClickListener(new twisterOnClick());
		twister_love.setOnClickListener(new twisterOnClick());
		twister_funny.setOnClickListener(new twisterOnClick());
		twister_child.setOnClickListener(new twisterOnClick());
		twister_word.setOnClickListener(new twisterOnClick());
		twister_english.setOnClickListener(new twisterOnClick());
		twister_test1.setOnClickListener(new twisterOnClick());
		twister_test2.setOnClickListener(new twisterOnClick());
	}

	public void initSaying() {
		TextView saying_classic;
		TextView saying_animal;
		TextView saying_love;
		TextView saying_funny;
		TextView saying_child;
		TextView saying_word;
		TextView saying_english;
		TextView saying_test1;
		TextView saying_test2;
		saying_classic = (TextView) findViewById(R.id.activity_main_tv_saying_1);
		saying_animal = (TextView) findViewById(R.id.activity_main_tv_saying_2);
		saying_love = (TextView) findViewById(R.id.activity_main_tv_saying_3);
		saying_funny = (TextView) findViewById(R.id.activity_main_tv_saying_4);
		saying_child = (TextView) findViewById(R.id.activity_main_tv_saying_5);
		saying_word = (TextView) findViewById(R.id.activity_main_tv_saying_6);
		saying_english = (TextView) findViewById(R.id.activity_main_tv_saying_7);
		saying_test1 = (TextView) findViewById(R.id.activity_main_tv_saying_8);
		saying_test2 = (TextView) findViewById(R.id.activity_main_tv_saying_9);
		saying_classic.setOnClickListener(new sayingOnClick());
		saying_animal.setOnClickListener(new sayingOnClick());
		saying_love.setOnClickListener(new sayingOnClick());
		saying_funny.setOnClickListener(new sayingOnClick());
		saying_child.setOnClickListener(new sayingOnClick());
		saying_word.setOnClickListener(new sayingOnClick());
		saying_english.setOnClickListener(new sayingOnClick());
		saying_test1.setOnClickListener(new sayingOnClick());
		saying_test2.setOnClickListener(new sayingOnClick());
	}

	class riddleOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			Animation shake = AnimationUtils.loadAnimation(context,
					R.anim.shake);
			v.startAnimation(shake);
			Bundle bundle = new Bundle();
			Intent intent = new Intent(MainActivity.this, RiddleActivity.class);
			switch (v.getId()) {
			case R.id.activity_main_tv_riddle_classic:
				bundle.putInt("flag", 1);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_animal:
				bundle.putInt("flag", 2);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_love:
				bundle.putInt("flag", 3);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_funny:
				bundle.putInt("flag", 4);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_child:
				bundle.putInt("flag", 5);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_word:
				bundle.putInt("flag", 6);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_english:
				bundle.putInt("flag", 7);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_riddle_8:
				bundle.putInt("flag", 8);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;

			case R.id.activity_main_tv_riddle_9:
				Toast.makeText(MainActivity.this, "敬请期待!", Toast.LENGTH_SHORT)
						.show();

				break;

			default:
				break;
			}

		}
	}

	class twisterOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			Animation shake = AnimationUtils.loadAnimation(context,
					R.anim.shake);
			v.startAnimation(shake);
			Bundle bundle = new Bundle();
			Intent intent = new Intent(MainActivity.this, TwisterActivity.class);
			switch (v.getId()) {
			case R.id.activity_main_tv_twister_1:
				bundle.putInt("flag", 11);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_2:
				bundle.putInt("flag", 12);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_3:
				bundle.putInt("flag", 13);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_4:
				bundle.putInt("flag", 14);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_5:
				bundle.putInt("flag", 15);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_6:
				bundle.putInt("flag", 16);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_7:
				bundle.putInt("flag", 17);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_twister_8:
				bundle.putInt("flag", 18);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;

			case R.id.activity_main_tv_twister_9:
				Toast.makeText(MainActivity.this, "敬请期待!", Toast.LENGTH_SHORT)
						.show();

				break;

			default:
				break;
			}

		}
	}

	class sayingOnClick implements OnClickListener {
		public void onClick(View v) {
			Context context = v.getContext();
			Animation shake = AnimationUtils.loadAnimation(context,
					R.anim.shake);
			v.startAnimation(shake);
			Bundle bundle = new Bundle();
			Intent intent = new Intent(MainActivity.this, SayingActivity.class);
			switch (v.getId()) {
			case R.id.activity_main_tv_saying_1:
				bundle.putInt("flag", 21);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_2:
				bundle.putInt("flag", 22);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_3:
				bundle.putInt("flag", 23);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_4:
				bundle.putInt("flag", 24);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_5:
				bundle.putInt("flag", 25);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_6:
				bundle.putInt("flag", 26);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_7:
				bundle.putInt("flag", 27);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.activity_main_tv_saying_8:
				bundle.putInt("flag", 28);
				bundle.putInt("flag_Id", -1);
				intent.putExtras(bundle);
				startActivity(intent);
				MainActivity.this.finish();

				break;

			case R.id.activity_main_tv_saying_9:
				Toast.makeText(MainActivity.this, "敬请期待!", Toast.LENGTH_SHORT)
						.show();

				break;

			default:
				break;
			}

		}
	}

	public void changeStyle(View v) {
		int color;
		Drawable drawable;
		Resources resources = this.getResources();
		i++;
		System.out.println("=======i=======" + i);
		if (i % 2 == 1) {
			System.out.println("=======i % 2=======" + i % 2);
			drawable = resources.getDrawable(R.drawable.sun);
			styleSrc.setImageDrawable(drawable);
			color = getResources().getColor(R.color.color_moon);
			main_background.setBackgroundColor(color);
		} else {
			System.out.println("=======i % 2=======" + i % 2);
			drawable = resources.getDrawable(R.drawable.moon_yellow);
			styleSrc.setImageDrawable(drawable);
			color = getResources().getColor(R.color.color_sun);
			main_background.setBackgroundColor(color);
		}
	}

	public void showLoveRiddle(View v) {
		Intent intent = new Intent(this, MyRiddleActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("flag_showlove", 1);
		intent.putExtras(bundle);
		startActivity(intent);
		this.finish();
	}

	public void showLoveTwister(View v) {
		Intent intent = new Intent(this, MyTwisterActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("flag_showlove", 1);
		intent.putExtras(bundle);
		startActivity(intent);
		this.finish();
	}

	public void showLoveSaying(View v) {
		Intent intent = new Intent(this, MySayingActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("flag_showlove", 1);
		intent.putExtras(bundle);
		startActivity(intent);
		this.finish();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Dialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("您确定要退出乐趣吗？")
					.setPositiveButton("后悔了",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							})

					.setNegativeButton("去意已绝",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									MainActivity.this.finish();
								}
							}).create();
			alertDialog.show();

		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Ckm.getInstance().stopMessage(this);
	}
}
