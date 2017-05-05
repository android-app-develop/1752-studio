package com.bingo.joy.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.bingo.joy.R;
import com.bingo.joy.db.DBRiddleManager;
import com.bingo.joy.db.DBSayingManager;
import com.bingo.joy.db.DBTwisterManager;
import com.bingo.joy.model.Riddle;
import com.bingo.joy.model.Saying;
import com.bingo.joy.model.Twister;
import com.bingo.util.Preference;

public class FlashActivity extends Activity {
	private Timer timer;

	// private ViewGroup container;

	// 关于数据操作
	private DBRiddleManager dbRManager = null;
	private DBTwisterManager dbTManager = null;
	private DBSayingManager dbSManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_activity);

		timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				initView();
			}
		};
		timer.schedule(task, 1000 * 4);

		// 数据库操作
		/* 开启一个新线程，在新线程里执行耗时的方法 */
		new Thread(new Runnable() {
			public void run() {
				// if(AppInstalledUtil.isAppInstalled(getApplicationContext(),
				// "com.bingo.lattern"))
				// {
				// System.out.println("========已安装！==");
				// }
				// else {
				// initData();
				// }

				SharedPreferences sharedPreferences = getSharedPreferences(
						"share", MODE_PRIVATE);

				boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun",
						true);
				Editor editor = sharedPreferences.edit();

				if (isFirstRun) {
					Log.e("debug", "第一次运行");
					editor.putBoolean("isFirstRun", false);
					Preference.saveBooleanData(getApplicationContext(),
							"class_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"animal_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"love_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"funny_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"child_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"word_paid", false);
					Preference.saveBooleanData(getApplicationContext(),
							"english_paid", false);
					editor.commit();
					initRiddleData();
					initTwisterData();
					initSayingData();
				} else {
					Log.e("debug", "不是第一次运行");
				}
				// 耗时的方法
			}
		}).start();

	}

	private void initView() {
		Intent intent = new Intent(FlashActivity.this, MainActivity.class);
		startActivity(intent);

		this.finish();
	}

	private void initRiddleData() {
		dbRManager = new DBRiddleManager(this);

		Riddle riddle1 = new Riddle("烈士美名传千古(打一诗句)", " 留取丹心照汗青", "经典", "无");
		Riddle riddle2 = new Riddle("孝心感动天(打一地名)", " 孝感", "经典", "无");
		Riddle riddle3 = new Riddle("臭豆腐(打一个明星名字)", " 莫文蔚(莫闻味)", "经典", "无");
		Riddle riddle4 = new Riddle("小姑娘(猜一个字)", " 妙", "经典", "无");
		Riddle riddle5 = new Riddle("冷冻室(打一礼貌用语)", " 寒舍", "经典", "无");
		Riddle riddle6 = new Riddle("什么书你最让人嫉妒？(猜一猜)", " 秘书", "经典", "无");
		Riddle riddle7 = new Riddle("一个老头不睡觉，从不说话把头摇(打一物)", " 不倒翁", "经典", "无");
		Riddle riddle8 = new Riddle("哪里的妞最多(猜一猜)", " 奶牛(妞)场", "经典", "无");
		Riddle riddle9 = new Riddle("望儿山上众人行，慈母馆内缅恩情(打一节日)", " 母亲节", "经典", "无");
		Riddle riddle10 = new Riddle("最新什么路不能走 (猜一猜)", " 寻常路(不走寻常路)", "经典", "无");
		Riddle riddle11 = new Riddle("男人之所(打一国外城市)", " 汉城", "经典", "无");
		Riddle riddle12 = new Riddle("妇女们在不知不觉中丢失掉的东西是什么？(猜一猜)", " 美貌", "经典",
				"无");
		Riddle riddle13 = new Riddle("航空公司开张(猜一成语)", " 有机可乘", "经典", "无");
		Riddle riddle14 = new Riddle("隔河相望，终到相聚时(打一七夕景观)", " 牵牛星、织女星相会", "经典",
				"无");
		Riddle riddle15 = new Riddle("大伟在电影最精彩的时候却上厕所，为什么？(猜一猜)", " 因为大伟没有看电影",
				"经典", "无");
		Riddle riddle16 = new Riddle("凸眼睛，阔嘴巴，尾巴要比身体大，碧绿水草衬着它，好像一朵大红花。(打一动物)",
				" 金鱼", "经典", "无");
		Riddle riddle17 = new Riddle("虚心向人请教(打一学科)", " 化学", "经典", "无");
		Riddle riddle18 = new Riddle("风平浪静(打一城市名)", " 宁波", "经典", "无");
		Riddle riddle19 = new Riddle("相反世界(打一物品)", " 镜子", "经典", "无");
		Riddle riddle20 = new Riddle("捂(打一电影名)", " 我的左手", "经典", "无");
		Riddle riddle21 = new Riddle("红红如玛瑙，营养又美味(打一水果)", " 樱桃", "经典", "无");
		Riddle riddle22 = new Riddle("别听鬼话(打一离合字)", " 信人言", "经典", "无");
		Riddle riddle23 = new Riddle("徒留昔日的帝王黯然神伤(打一明星)", " 刘惜君", "经典", "无");
		Riddle riddle24 = new Riddle("新媳妇下花轿(打一成语)", " 任人摆布", "经典", "无");
		Riddle riddle25 = new Riddle("自小在一起，目前少联系(猜一字)", " 省", "经典", "无");
		Riddle riddle26 = new Riddle("曹植因何七步诗 (三字常言一)", " 老大难", "经典", "无");
		Riddle riddle27 = new Riddle("貂蝉莫垂涕(打一热门电视剧)", " 《美人无泪》", "经典", "无");
		Riddle riddle28 = new Riddle("天下第一 (打香烟商标一)", " 大前门", "经典", "无");
		Riddle riddle29 = new Riddle("初始的日子(打一节日)", " 元旦", "经典", "无");
		Riddle riddle30 = new Riddle("拱手让人 (猜一字)", " 供", "经典", "无");
		Riddle riddle31 = new Riddle("来了施拳脚(打一湖南地名)", " 临武", "经典", "无");
		Riddle riddle32 = new Riddle("没有住人的城市(打一歌曲名)", " 《空城》", "经典", "无");
		Riddle riddle33 = new Riddle("自杀之都(打一外国城市)", " 拉斯维加斯", "经典", "无");
		Riddle riddle34 = new Riddle("两地相思 (猜一字)", " 桂", "经典", "无");
		Riddle riddle35 = new Riddle("太平间的解剖医生(打一离合字)", " 屠尸者", "经典", "无");
		Riddle riddle36 = new Riddle("四处游荡走不掉，人人需要它围绕(打一自然物)", " 空气", "经典", "无");
		Riddle riddle37 = new Riddle("冰到底是什么东西？(猜一猜)", " 一块一块的水", "经典", "无");
		Riddle riddle38 = new Riddle("水陆各半(打一拉丁美洲国家名)", " 海地", "经典", "无");
		Riddle riddle39 = new Riddle("古老乐器民族舞，苗族文化入人心(打一乐器)", " 芦笙", "经典", "无");
		Riddle riddle40 = new Riddle("轻柔丝滑如蝉翼，苏杭一带最有名(打一物品)", " 丝绸", "经典", "无");
		Riddle riddle41 = new Riddle("美美晚餐好心情，熊熊火炉暖人心(打一国外节日)", " 平安夜", "经典",
				"无");
		Riddle riddle42 = new Riddle("春暖花开来踏青，寒食上墓忆前人(打一节日)", " 清明节", "经典", "无");
		Riddle riddle43 = new Riddle("古人对月亮的崇拜(打一节日)", " 中秋节", "经典", "无");
		Riddle riddle44 = new Riddle("落叶乔木，见血封喉(打一植物)", " 毒箭树", "经典", "无");
		Riddle riddle45 = new Riddle("绿豆自杀，从六楼跳了下来,流了很多血,怎么样了？(猜一猜)", " 变成了红豆",
				"经典", "无");
		Riddle riddle46 = new Riddle("张着小雨伞,常站大树下，干湿都能吃，味美营养大(打一菌类)", " 蘑菇",
				"经典", "无");
		Riddle riddle47 = new Riddle("小小绿叶水里生，水珠爱在上面玩(打一植物)", " 荷叶", "经典", "无");
		Riddle riddle48 = new Riddle("一件东西大无边，能装三百多个天，还装月亮十二个，它换衣服过新年。(打一物)",
				" 日历", "经典", "无");
		Riddle riddle49 = new Riddle("时而成烟，时而成线，遇见了海，还能变盐(打一自然物体)", " 水", "经典",
				"无");
		Riddle riddle50 = new Riddle("此物老家在非洲，力大气壮赛过牛，血盆大口吼一声，吓得百兽都发抖。(打一动物)",
				" 狮子", "经典", "无");
		Riddle riddle51 = new Riddle("心惊胆战(打一离合字)", " 心具惧", "经典", "无");
		Riddle riddle52 = new Riddle("整天都是太阳(打一城市)", " 拉萨(拉萨称为“日光城”)", "经典",
				"无");
		Riddle riddle53 = new Riddle("十个哥哥 (猜一个字)", " 克", "经典", "无");
		Riddle riddle54 = new Riddle("沙丘之上，烽火不停(打一诗句)", " 《使至塞上》", "经典", "无");
		Riddle riddle55 = new Riddle("反胃(打三字常用语)", " 吃不消", "经典", "无");
		Riddle riddle56 = new Riddle("白胖娃娃，又圆又滑，东蹦西跳，总是挨打", " 乒乓球", "经典", "无");
		Riddle riddle57 = new Riddle("掘墓鞭尸报父仇，门客挖眼挂东门(打一历史人物)", " 伍子胥", "经典",
				"无");
		Riddle riddle58 = new Riddle("输氧(打一离合字)", " 乞一气", "经典", "无");
		Riddle riddle59 = new Riddle(
				"饭店里有三个女人在吃香肠：一个是咬着吃的，一个是舔着吃的，一个是裹着吃的，请问这三个女人哪个结过婚？(猜一猜)",
				" 戴戒指的那个女人结过婚", "经典", "无");
		Riddle riddle60 = new Riddle("小明和朋友去海边玩，突然海浪把小明卷走，请问小明的朋友叫什么？(猜一猜)",
				" 叫救命", "经典", "无");
		Riddle riddle61 = new Riddle("晚唐长安诞才女，未入正史传千古(打一女诗人)", " 鱼玄机", "经典",
				"无");
		Riddle riddle62 = new Riddle("海市蜃楼(打一李白诗句)", " 眼前有景道不得", "经典", "无");
		Riddle riddle63 = new Riddle("既能投笔又能从戎(猜一猜)", " 刘", "经典", "无");
		Riddle riddle64 = new Riddle("翻跟斗最在行，长有六张脸，还有二十一只眼(打一物)", " 骰子", "经典",
				"无");
		Riddle riddle65 = new Riddle("身子黑不溜秋，喜往泥里嬉游，常爱口吐气泡，能够观察气候。(打一动物)",
				" 泥鳅", "经典", "无");
		Riddle riddle66 = new Riddle("脱去黄金袍，露出白玉体，身子比豆小，名字有三尺(猜一猜)", " 大米",
				"经典", "无");
		Riddle riddle67 = new Riddle("女性的内裤(打一食品)", " 果冻(食品)", "经典", "无");
		Riddle riddle68 = new Riddle("撼山易,撼岳家军难!(打一清代文人)", " 金圣叹", "经典", "无");
		Riddle riddle69 = new Riddle("是自己的，却是别人用的最多(打一事物)", " 名字", "经典", "无");
		Riddle riddle70 = new Riddle("静姝一枚，小家碧玉(打一奥运明星)", " 吴静钰", "经典", "无");
		Riddle riddle71 = new Riddle("三月节里何所期，真心日月故人离(打一节日)", " 清明节", "经典", "无");
		Riddle riddle72 = new Riddle("春耕春种好时节，墓祭之礼把情谢(打一节日)", " 清明节", "经典", "无");
		Riddle riddle73 = new Riddle("一孔之见(打一物品)", " 显微镜", "经典", "无");
		Riddle riddle74 = new Riddle("作家报警(打一离合字)", " 文求救", "经典", "无");
		Riddle riddle75 = new Riddle("草原上的女王城(打一外国城市)", " 丹佛", "经典", "无");
		Riddle riddle76 = new Riddle("在门口流了一夜的鼻涕(打一成语)", " 一夜风流", "经典", "无");
		Riddle riddle77 = new Riddle("坏人聚居，无人举报。(打一地址)", " 监狱", "经典", "无");
		Riddle riddle78 = new Riddle("玩Blog的高手(打一火热电视剧)", " 《微博达人》", "经典", "无");
		Riddle riddle79 = new Riddle("梦回归到烟雨之中(打一地区)", " 江南", "经典", "无");
		Riddle riddle80 = new Riddle("梅花满天飞，芳香扑鼻来(打一诗句)", " 遥知不是雪，为有暗香来", "经典",
				"无");

		Riddle riddle81 = new Riddle("青蛙为什么能比树跳得高？(猜一猜)", " 因为树不会跳", "动物", "无");
		Riddle riddle82 = new Riddle("小小白姑娘,住在硬壳乡,做成盘中餐,味美扑鼻香(打一动物)", " 田螺",
				"动物", "无");
		Riddle riddle83 = new Riddle("穿着盔甲爱钻洞，全身是宝大家爱(打一动物)", " 穿山甲", "动物", "无");
		Riddle riddle84 = new Riddle("美丽无比似女神，求爱时来把屏开(打一动物)", " 孔雀", "动物", "无");
		Riddle riddle85 = new Riddle("朝为越溪女，暮作吴宫妃探骊格 (猜一猜)", " 泊人·时迁", "动物",
				"无");
		Riddle riddle86 = new Riddle("贼头贼脑爱偷油,花猫一叫赶紧溜(打一动物)", " 老鼠", "动物", "无");
		Riddle riddle87 = new Riddle("五毒之首千足虫，若咬一口鲜血涌(打一动物)", " 蜈蚣", "动物", "无");
		Riddle riddle88 = new Riddle("远看像只猫，捕猎身手好。游泳又爬树，不吃青青草(打一动物)", " 豹",
				"动物", "无");
		Riddle riddle89 = new Riddle("生肖中，最骇人的是哪3个？(猜一猜)", " 猪、龙、鸡(朱熔基)", "动物",
				"无");
		Riddle riddle90 = new Riddle("有头没有颈，身上冷冰冰，有翅不能飞，无脚也能行(打一动物)。", " 鱼",
				"动物", "无");
		Riddle riddle91 = new Riddle("懂得 (打一动物)", " 知了(蝉)", "动物", "无");
		Riddle riddle92 = new Riddle("小英雄，爱捉虫，冬天一来躲进洞(打一动物)", " 青蛙", "动物", "无");
		Riddle riddle93 = new Riddle("身穿白袍戴红帽，脖长走路摇啊摇。扁嘴有翅水陆行，红掌爱把清波扰。(打一动物)",
				" 鹅", "动物", "无");
		Riddle riddle94 = new Riddle("左右不分，正反不知(打一动物)", " 鱼", "动物", "无");
		Riddle riddle95 = new Riddle("羽毛鲜艳呈七色，却爱花粉小八哥(打一动物)", " 虹彩吸蜜鹦鹉", "动物",
				"无");
		Riddle riddle96 = new Riddle("黑黑眼圈大胖子，最爱林中把竹吃(打一动物)", " 熊猫", "动物", "无");
		Riddle riddle97 = new Riddle("徐妃用好酒(打一动物)", " 蛐蟮", "动物", "无");
		Riddle riddle98 = new Riddle("有脚不会走路(打一动物)", " 蜻蜓", "动物", "无");
		Riddle riddle99 = new Riddle("卸甲方知年事高(打一动物)", " 牛", "动物", "无");
		Riddle riddle100 = new Riddle("敏锐眼睛弯弯嘴，用爪捕食它最会(打一动物)", " 老鹰", "动物", "无");
		Riddle riddle101 = new Riddle("海中狼(打一动物)", " 鲨鱼", "动物", "无");
		Riddle riddle102 = new Riddle("一位小姑娘,身穿花衣裳，百花是朋友，春天聚会忙(打一昆虫)", " 蝴蝶",
				"动物", "无");
		Riddle riddle103 = new Riddle("身边嗅一嗅,专把毒品搜,警察夸奖它,是个好帮手(打一动物)", " 缉毒犬",
				"动物", "无");
		Riddle riddle104 = new Riddle("左边有一只公羊右边有一只母羊中间有一只小羊为什么狼吃了小羊",
				" 公羊和母羊都是雕塑", "动物", "无");
		Riddle riddle105 = new Riddle("一个长鼻子，长长猴尾巴，变色龙眼睛，夜夜水中游(打一动物)", " 海马",
				"动物", "无");
		Riddle riddle106 = new Riddle("春天睡觉，夏天出来，趴在树上，整天吵闹(打一昆虫动物)", " 蝉(知了)",
				"动物", "无");
		Riddle riddle107 = new Riddle("小小鱼儿五个角(打一动物)", " 海星", "动物", "无");
		Riddle riddle108 = new Riddle("身笨力气大，干活常带枷，春耕和秋种，不能缺少它。(打一动物)", " 牛",
				"动物", "无");
		Riddle riddle109 = new Riddle("迎风飞千里,能把信息送,城市和门牌,它都记得清(打一动物)", " 信鸽",
				"动物", "无");
		Riddle riddle110 = new Riddle("爱游泳，季节变，皮毛跟着变(打一动物)", " 水獭", "动物", "无");
		Riddle riddle111 = new Riddle("一位姑娘真漂亮,橘红衣裳披上身.嵌上七颗黑星星，蚜虫见了无影踪。(打一动物)",
				" 瓢虫", "动物", "无");
		Riddle riddle112 = new Riddle(
				"鹿的角，牛的蹄，天生喜水又喜泥。骆驼脖子驴的尾，回到故乡来定居。( 打一动物)", " 麋鹿", "动物", "无");
		Riddle riddle113 = new Riddle("黑身红嘴卷羽毛，长脖像2很高贵(打一鸟类)", " 黑天鹅", "动物",
				"无");
		Riddle riddle114 = new Riddle("关公的命很不好，这是为什么啊？(猜一猜)", " 红颜薄命(红脸关公)",
				"动物", "无");
		Riddle riddle115 = new Riddle("引力检测(打一动物)", " 考拉", "动物", "无");
		Riddle riddle116 = new Riddle("一生都在忙,飞在百花乡,围着花儿转，花汁变蜜糖(打一昆虫)", " 蜜蜂",
				"动物", "无");
		Riddle riddle117 = new Riddle("长着尖尖牙，森林称霸王(打一动物)", " 老虎", "动物", "无");
		Riddle riddle118 = new Riddle("什么东西装玻璃，爱把鼻子当马骑？(猜一猜)", " 眼镜", "动物", "无");
		Riddle riddle119 = new Riddle("长长脖子同树高，身披白纹褐斑点，又长又细四条腿(打一动物)", " 长颈鹿",
				"动物", "无");
		Riddle riddle120 = new Riddle("小小飞蛾好夜游，闪闪屁股诱异性(打一动物)", " 萤火虫", "动物",
				"无");
		Riddle riddle121 = new Riddle("绿色刀郎三角头，大眼明亮雌性强(打一动物)", " 螳螂", "动物", "无");
		Riddle riddle122 = new Riddle("养的最多的鸡是什么鸡(猜一猜)", " 手机(手鸡 人手一部)", "动物",
				"无");
		Riddle riddle123 = new Riddle("大大红掌水中摆，长长脖子一排排(打一动物)", " 鹅", "动物", "无");
		Riddle riddle124 = new Riddle("浑身雪翼腿儿黑，最喜成群上青天(打一动物)", " 白鹭", "动物", "无");
		Riddle riddle125 = new Riddle(
				"题目：昨夜蟋蟀鸣不停，惊回梦里已三更。起来独自绕街行，悄悄窗外月胧明。(猜一猜)", " 老鼠", "动物", "无");
		Riddle riddle126 = new Riddle("头顶大黑帽，身着燕尾服。(打一动物)", " 企鹅", "动物", "无");
		Riddle riddle127 = new Riddle("败得真窝囊(打一动物)", " 北极熊", "动物", "无");
		Riddle riddle128 = new Riddle("下面出场的这位嘉宾可是我们中国男人的骄傲,是个歌星,你猜是谁", " 古巨鸡",
				"动物", "无");
		Riddle riddle129 = new Riddle("谁最喜欢咬文嚼字？(猜一猜)", " 蛀书虫", "动物", "无");
		Riddle riddle130 = new Riddle("什么东西越削越粗(猜一猜)", " 井、土削的越多，洞就越大(粗)",
				"动物", "无");
		Riddle riddle131 = new Riddle("色彩鲜艳丛中飞，鳞片雨衣格外美(打一动物)", " 蝴蝶", "动物", "无");
		Riddle riddle132 = new Riddle("全身雪白尾翼黑，红冠细腿白富美(打一动物)", " 丹顶鹤", "动物",
				"无");
		Riddle riddle133 = new Riddle("妈妈长得怪，肚前挂口袋，蹦蹦跳跳真可爱(打一动物)", " 袋鼠", "动物",
				"无");
		Riddle riddle134 = new Riddle("小小家伙眼睛亮，花中采宝回家酿(打一动物)", " 蜜蜂", "动物", "无");
		Riddle riddle135 = new Riddle("珍贵的动物打一生肖(猜一猜)", " 龙", "动物", "无");
		Riddle riddle136 = new Riddle("弯弯嘴儿亮眼睛，捕捉野兔它最行；千里眼儿看得清，一口吞下便清零(打一动物)",
				" 鹰", "动物", "无");
		Riddle riddle137 = new Riddle("驼背老公公，胡子毛烘烘，热火锅里去洗澡，青袍换成大给红袍。(猜一猜)",
				" 虾", "动物", "无");
		Riddle riddle138 = new Riddle("古时明月虫影飞，世代草木永相随(打一动物)",
				" 蝴蝶(虫+古+月=蝴，虫+世+木=蝶)", "动物", "无");
		Riddle riddle139 = new Riddle("脖子长，爱吃鱼，天天吃鱼吐出来(打一动物)", " 鸬鹚", "动物", "无");
		Riddle riddle140 = new Riddle("虽然不说话,本领呱呱叫,鼻子特别灵,破案功劳大(打一动物)", " 警犬",
				"动物", "无");
		Riddle riddle141 = new Riddle("软软绒毛白又白,两只耳朵竖起来。红红眼睛三瓣嘴，爱吃萝卜爱吃菜(打一动物)",
				" 小白兔", "动物", "无");
		Riddle riddle142 = new Riddle("前有毒夹，后有尾巴， 全身二十一节，中药铺要它。(打一动物)", " 蜈蚣",
				"动物", "无");
		Riddle riddle143 = new Riddle("小小个头三尺高，头顶羽冠模样俏，爱把锦袍来炫耀(打一动物)", " 孔雀",
				"动物", "无");
		Riddle riddle144 = new Riddle("小笋头上冒绿芽(打一动物)", " 竹叶青", "动物", "无");
		Riddle riddle145 = new Riddle("神来帽脱下 ，革命力量大  (打两个字)", " 米勒", "动物", "无");
		Riddle riddle146 = new Riddle("北岳恒山探骊格(猜一猜)", " 名胜·丈人峰", "动物", "无");
		Riddle riddle147 = new Riddle("一个白发老妈妈，走起路来四边爬，不用铁鎝不用锄，种下一片好芝麻(打一动物)",
				" 蚕蛾", "动物", "无");
		Riddle riddle148 = new Riddle("旭日阳刚成员主唱原创单曲(打一动物)", " 麻雀", "动物", "无");
		Riddle riddle149 = new Riddle("小时候有尾巴，长大后尾巴不见了(打一动物)", " 青蛙", "动物", "无");
		Riddle riddle150 = new Riddle("天上的眼睛掉进水里(打一动物)", " 海星", "动物", "无");
		Riddle riddle151 = new Riddle("最接近人类的动物是什么？(猜一猜)", " 虱子", "动物", "无");
		Riddle riddle152 = new Riddle("过去拉犁干活快,如今栏里挤牛奶。过去拉粮几千斤,如今肉嫩餐桌摆(打一动物)",
				" 牛", "动物", "无");
		Riddle riddle153 = new Riddle("浑身肌肉最爱跳，胸前大兜装宝宝(打一动物)", " 袋鼠", "动物", "无");
		Riddle riddle154 = new Riddle("小小蘑菇海里游，会发光来不怕黑(打一动物)", " 水母", "动物", "无");
		Riddle riddle155 = new Riddle("羊，猪，熊，狗，猴哪一个动物用眉毛呼吸", " 羊(扬(羊)眉吐气)",
				"动物", "无");
		Riddle riddle156 = new Riddle("双亲不同种，杂交不生育(打一动物)", " 骡子", "动物", "无");
		Riddle riddle157 = new Riddle("刚出生的婴儿(打一动物)", " 猫头鹰(毛头婴)", "动物", "无");
		Riddle riddle158 = new Riddle("小小一头牛,不拉犁和耧。力气虽不大，背着房子走(打一动物)", " 蜗牛",
				"动物", "无");
		Riddle riddle159 = new Riddle("小小脑袋三根毛，五彩羽毛似扇子(打一动物)", " 孔雀", "动物", "无");
		Riddle riddle160 = new Riddle("形似小飞机,飞东又飞西。夏天吃蚊虫，还能报天气。(打一动物)", " 蜻蜓",
				"动物", "无");

		Riddle riddle161 = new Riddle("八姑娘(打一称谓)", " 空中小姐", "爱情", "无");
		Riddle riddle162 = new Riddle("姑娘月下摆瓜果，祈求收获好姻缘。(打一节日)", " 七夕节", "爱情",
				"无");
		Riddle riddle163 = new Riddle("有一个人到国外去，为什么他周围的都是中国人？(猜一猜)",
				" 外国人到了中国", "爱情", "无");
		Riddle riddle164 = new Riddle("在厕所遇见朋友时，最好不要问哪一句话？(猜一猜)", " 你吃了吗",
				"爱情", "无");
		Riddle riddle165 = new Riddle("人为什么要走去床上睡觉呢(猜一猜)", " 因为床不会自己走过来", "爱情",
				"无");
		Riddle riddle166 = new Riddle("没人理 打一明星 (猜一猜)", " 任贤齐", "爱情", "无");
		Riddle riddle167 = new Riddle("湖中把手分(打一国家)", " 古巴", "爱情", "无");
		Riddle riddle168 = new Riddle("妻子认错(打一服装)", " 太太服", "爱情", "无");
		Riddle riddle169 = new Riddle("纤夫的爱(打三字常用语)", " 拉人情", "爱情", "无");
		Riddle riddle170 = new Riddle("妈妈最讨厌哪种鸭蛋？(猜一猜)", " 考卷上的鸭蛋", "爱情", "无");
		Riddle riddle171 = new Riddle("七仙女爱上董永(打一诗句)", " 只羡鸳鸯不羡仙", "爱情", "无");
		Riddle riddle172 = new Riddle(
				"寒山寺上一棵竹，不能做称有人用，此言非虚能兑现，只要有情雨下显，天鹅一出鸟不见。(猜一猜)", " 等你说爱我",
				"爱情", "无");
		Riddle riddle173 = new Riddle(
				"日长夜短愁几许，高处无口几人来，一人游弋芳草地，十士脚长披蓑衣，天鹅展翅鸟已飞，白勺烹酒无意义，空余一钩三点雨!(猜一猜)",
				" 月亮代表我的心", "爱情", "无");
		Riddle riddle174 = new Riddle(
				"鸟飞鹅跳，月上中梢，目上朱砂，已异非巳，勺旁傍白，万事开头，工戈不全，雨下挚友，称断人和。(猜一猜)",
				" 我用自己的方式爱你", "爱情", "无");
		Riddle riddle175 = new Riddle("有两只蜜蜂很相爱,后来母蜜蜂却嫁给了蜘蛛为什么(猜一猜)",
				" 因为这只母蜜蜂爱上网", "爱情", "无");
		Riddle riddle176 = new Riddle("热恋中的情侣(打一花卉)", " 鹤望兰", "爱情", "无");
		Riddle riddle177 = new Riddle("恋(打一生物用语)", " 半变态", "爱情", "无");
		Riddle riddle178 = new Riddle("心心相印(打二字常用语)", " 体贴", "爱情", "无");
		Riddle riddle179 = new Riddle("黄昏恋和大龄剩女的恋爱史(打一电视剧)", " 《我和老妈一起嫁》",
				"爱情", "无");
		Riddle riddle180 = new Riddle("麻辣情感小夫妻，都市宝宝惹争议(打一热门电影)", " 《小儿难养》",
				"爱情", "无");
		Riddle riddle181 = new Riddle("绑架我爱上你(打一电影)", " 《边境风云》", "爱情", "无");
		Riddle riddle182 = new Riddle("嫁个傻丈夫,常常发牢骚(打四字成语)", " 痴男怨女", "爱情", "无");
		Riddle riddle183 = new Riddle("洁白的爱(打一花卉)", " 葱兰", "爱情", "无");
		Riddle riddle184 = new Riddle("在早餐时从来不吃的是什么？(猜一猜)", " 午餐和晚餐", "爱情", "无");
		Riddle riddle185 = new Riddle("糊涂姻缘(打一通假字谜)", " 昏通婚", "爱情", "无");
		Riddle riddle186 = new Riddle("月露良宵拜魁星，老牛庆生也不迟(打一节日)", " 七夕节", "爱情",
				"无");
		Riddle riddle187 = new Riddle("大律师与五位妻子间的爱恨情仇(打一热门电视剧)", " 《名媛望族》",
				"爱情", "无");
		Riddle riddle188 = new Riddle("树儿睁开眼，小子屋下眠，良心缺一点，日落残兔边。(猜一猜)", " 相见恨晚",
				"爱情", "无");
		Riddle riddle189 = new Riddle("折磨人的东西，男女为之所困(打一名词)", " 爱情", "爱情", "无");
		Riddle riddle190 = new Riddle("一只蚂蚁居然从四川爬到了东京，可能吗？(猜一猜)", " 可能从地图上",
				"爱情", "无");
		Riddle riddle191 = new Riddle("在绝境中萌生的爱情(打一电影)", " 《最爱》", "爱情", "无");
		Riddle riddle192 = new Riddle("什么东西闭着眼睛可以看到睁着研究却看不到?(猜一猜)", " 梦", "爱情",
				"无");
		Riddle riddle193 = new Riddle("此情可待成追忆，只是当时已惘然(打一歌曲)", " 《有多少爱可以重来》",
				"爱情", "无");
		Riddle riddle194 = new Riddle("浪漫真情(打一花卉)", " 紫玫瑰", "爱情", "无");
		Riddle riddle195 = new Riddle(
				"天鹅飞去鸟不归，怀念昔日空费心，云开月下双匕影，水流几处又相逢，日落月出人倚月，单身贵族尔相随。(猜一猜)",
				" 我不能没有你", "爱情", "无");
		Riddle riddle196 = new Riddle("带上真心去相亲，闲人免谈(打一综艺节目)", " 非诚勿扰", "爱情",
				"无");
		Riddle riddle197 = new Riddle(
				"古树撑天枝难觅，怀抱可怜却无心，赵国有妃不是女，鹅毛轻飘鸟不见，远去不想囊羞涩，受尽苦难又换友，自称有人伴君旁(猜一猜)",
				" 对不起我还爱你", "爱情", "无");
		Riddle riddle198 = new Riddle("要爱爱我(打一国外节日)", " 圣诞节", "爱情", "无");
		Riddle riddle199 = new Riddle("为什么女人说男人都不是好东西？(猜一猜)", " 因为她之前没有遇到过好男人",
				"爱情", "无");
		Riddle riddle200 = new Riddle("十八岁的姑娘被锁深宫 (猜一个字)",
				" 困 (十八是木，深宫四面围墙所以是口)", "爱情", "无");
		Riddle riddle201 = new Riddle("最吉祥的男艺人是谁？(猜一猜)", " 陶喆", "爱情", "无");
		Riddle riddle202 = new Riddle("白衣天使爱上卧底警察(打一电视剧)", " 《暗线》", "爱情", "无");
		Riddle riddle203 = new Riddle("小两口本是同林鸟(打一交通用语)", " 禁鸣", "爱情", "无");
		Riddle riddle204 = new Riddle("请不要忘记我(打一花卉)", " 水仙花(土耳其花语)", "爱情", "无");
		Riddle riddle205 = new Riddle("我要与你过一辈子(打一花卉)", " 粉红蔷薇(花语)", "爱情", "无");
		Riddle riddle206 = new Riddle("小刚能4个小时不眨眼，为什么？(猜一猜)", " 因为他睡着了", "爱情",
				"无");
		Riddle riddle207 = new Riddle("我想有个家(打四字常用语)", " 希望所在", "爱情", "无");
		Riddle riddle208 = new Riddle(
				"淮海又见水退时，双人换走阻碍石，月顶右手不见口，青年男女树心旁，世上何物最懂爱  (猜一猜)", " 难得有情人",
				"爱情", "无");
		Riddle riddle209 = new Riddle("地狱里打官司 (打一成语)", " 死对头", "爱情", "无");
		Riddle riddle210 = new Riddle("有风不动无风动，不动无风动有风(猜一物)", " 扇子", "爱情", "无");
		Riddle riddle211 = new Riddle("守门员(猜一字)", " 闪", "爱情", "无");
		Riddle riddle212 = new Riddle("大明的英语非常好，可是有些老外却听不懂，为什么？(猜一猜)",
				" 日本人听他说英语", "爱情", "无");
		Riddle riddle213 = new Riddle("窈窕淑女，暗自求爱(打一奥运明星)",
				" 邱波(“暗自求爱”即为“暗送秋波”)", "爱情", "无");
		Riddle riddle214 = new Riddle("无告白不恋爱(打一电视剧)", " 《爱情是从告白开始的》", "爱情",
				"无");
		Riddle riddle215 = new Riddle("褪去的爱(打一花卉)", " 黄玫瑰", "爱情", "无");
		Riddle riddle216 = new Riddle("爱的誓言(打一花卉)", " 粉蔷薇", "爱情", "无");
		Riddle riddle217 = new Riddle("哑巴谈恋爱(打一歌名)", " 爱你在心口难开", "爱情", "无");
		Riddle riddle218 = new Riddle("罗马分手(打三字常用语)", " 别在意", "爱情", "无");
		Riddle riddle219 = new Riddle(
				"天鹅飞去鸟不归， 良字无头双人配； 受字中间多两笔(双木非林心相连)， 人尔结合就是自己。(猜一猜)",
				" 我很爱<想>你", "爱情", "无");
		Riddle riddle220 = new Riddle("半夜有行舟探骊格(猜一猜)", " 泊人一·时迁", "爱情", "无");
		Riddle riddle221 = new Riddle("小小绒球随风飘，深情爱意停不了(打一花卉)", " 蒲公英", "爱情",
				"无");
		Riddle riddle222 = new Riddle(
				"古树撑天枝难觅，怀抱可怜却无心，赵国有妃不是女，鹅毛轻飘鸟不见，远去不想囊羞涩，受尽苦难又换友，自称有人伴君旁。(猜一猜)",
				" 对不起我还爱你", "爱情", "无");
		Riddle riddle223 = new Riddle("什么数字最听话呢？(猜一猜)", " 100，百依百顺", "爱情", "无");
		Riddle riddle224 = new Riddle("弑夫女犯与情场骗子的闪恋(打一热门电影)", " 《晚秋》", "爱情",
				"无");
		Riddle riddle225 = new Riddle(
				"春雨季，梧桐树上结丝绸；夏日凉，两人阵中称英雄；秋风起，鸿雁传音数千里；冬雪飘，美女为何露半腰。(猜一猜)",
				" 绝对重要", "爱情", "无");
		Riddle riddle226 = new Riddle("帮妻子学文化(打一教育用语)", " 教室", "爱情", "无");
		Riddle riddle227 = new Riddle("30岁女教师和17岁高中生的危险恋情(打一电影)", " 《智齿》",
				"爱情", "无");
		Riddle riddle228 = new Riddle("小华的男朋友说小华吃的少小华却生气了为什么？(猜一猜)",
				" 他说小华肚量小，小华当然生气了", "爱情", "无");
		Riddle riddle229 = new Riddle("热恋中的情侣(打一诗句)", " 相看两不厌", "爱情", "无");
		Riddle riddle230 = new Riddle("你知道聪明和傻子的区别吗？(猜一猜)", " 傻子提问聪明", "爱情",
				"无");
		Riddle riddle231 = new Riddle("那种人一年只工作一次？(猜一猜)", " 圣诞老人", "爱情", "无");
		Riddle riddle232 = new Riddle("辰时下雨 地动山摇  (打一个字)", " 震", "爱情", "无");
		Riddle riddle233 = new Riddle(
				"春雨季梧桐树上结丝绸；夏日凉两人阵中称英雄；秋风起鸿雁传音数千里；冬雪飘美女为何露半腰。 (猜一猜)", " 绝对重要",
				"爱情", "无");
		Riddle riddle234 = new Riddle("刘烨胡军断背之恋(打一电影)", " 《蓝宇》", "爱情", "无");
		Riddle riddle235 = new Riddle("女人是男人的一半(打一数学用语)", " 公倍数", "爱情", "无");
		Riddle riddle236 = new Riddle("犬夜叉(打一个现在名词)", " 人妖", "爱情", "无");
		Riddle riddle237 = new Riddle(
				"纸上写着某个命令，但是，看到此文字的人绝对不能宣读命令。那么，纸上写的是什么呢？", " 不要念出此", "爱情", "无");
		Riddle riddle238 = new Riddle("我心载着你，不上你的船，已在不言中，双人日下行，燕子离鹅去，马口解猜疑",
				" 您还记得我吗", "爱情", "无");
		Riddle riddle239 = new Riddle("三角恋(打一热门电影)", " 2012最新电影：《危险关系》", "爱情",
				"无");
		Riddle riddle240 = new Riddle("泰山拉着树腾在丛林间穿梭时，为什么要扯着喉咙大叫？ (猜一猜)",
				" 他怕不相似的猴子迎面扑过来", "爱情", "无");

		Riddle riddle241 = new Riddle("句号(打四字常用语)", " 可圈可点", "搞笑", "无");
		Riddle riddle242 = new Riddle("拉出去的屎(打一成语)", " 有去无回", "搞笑", "无");
		Riddle riddle243 = new Riddle("房屋,宫殿,岩洞,大厦,牛棚,那个词与众不同？", " 岩洞，其它都是人工的",
				"搞笑", "无");
		Riddle riddle244 = new Riddle("八戒的屁股(打一明星)", " 朱茵", "搞笑", "无");
		Riddle riddle245 = new Riddle("只哼哼曲调,不唱歌词,为什么？(打一女明星)", " 吴佩慈(无配词)",
				"搞笑", "无");
		Riddle riddle246 = new Riddle("兔子路边走(打一城市)", " 蚌埠", "搞笑", "无");
		Riddle riddle247 = new Riddle("天黑了(打一歌名)", " 《夜夜夜夜》", "搞笑", "无");
		Riddle riddle248 = new Riddle("增肥特效药打一物品", " 肥料", "搞笑", "无");
		Riddle riddle249 = new Riddle("便秘的原因(打一好莱坞明星)", " 史泰龙", "搞笑", "无");
		Riddle riddle250 = new Riddle("小刚住一楼为什么还有恐高症？", " 他怕他妈妈，因为他妈妈姓高", "搞笑",
				"无");
		Riddle riddle251 = new Riddle("红冠黑嘴白衣裳，双腿细瘦走路晃，漫步水中捕鱼虾，凌空展翅能飞翔。(打一动物)",
				" 鹤", "搞笑", "无");
		Riddle riddle252 = new Riddle("屎壳郎糟蹋粮食(打一明星)", " 费翔", "搞笑", "无");
		Riddle riddle253 = new Riddle("下围棋(打一食品)", " 包子", "搞笑", "无");
		Riddle riddle254 = new Riddle("伯父(打称谓二)", " 爸爸、的哥", "搞笑", "无");
		Riddle riddle255 = new Riddle("男模走猫步(打一军事用语)", " 两面夹击", "搞笑", "无");
		Riddle riddle256 = new Riddle("玉环飞燕皆尘土(打一电视剧名)", " 《绝代双骄》", "搞笑", "无");
		Riddle riddle257 = new Riddle("狮子，老虎，骡子，鹿在大漠里面行走谁渴？(猜一猜)", " 鹿渴(look)",
				"搞笑", "无");
		Riddle riddle258 = new Riddle("怎样才能防止第二次感冒？(猜一猜)",
				" 得第二次感冒后就不会再有第二次感冒了", "搞笑", "无");
		Riddle riddle259 = new Riddle("九只鸟,猜一个字", " 鸠", "搞笑", "无");
		Riddle riddle260 = new Riddle("女儿经(打一花卉)", " 月月红", "搞笑", "无");
		Riddle riddle261 = new Riddle("做什么事会身不由己？(猜一猜)", " 做梦", "搞笑", "无");
		Riddle riddle262 = new Riddle("白弟弟，黑哥哥，排排坐，爱唱歌，丁冬，丁冬，快乐多(猜一猜)", " 钢琴",
				"搞笑", "无");
		Riddle riddle263 = new Riddle("一个风箱真奇怪，一拉它就唱起来。 (打一乐器)", " 手风琴", "搞笑",
				"无");
		Riddle riddle264 = new Riddle("别抖，稳住了再开枪。(八字口语)", " 不要动不动就发火", "搞笑",
				"无");
		Riddle riddle265 = new Riddle("耶稣是哪里的人？(猜一猜)", " 天国", "搞笑", "无");
		Riddle riddle266 = new Riddle("忐(打三字常用语)", " 上进心", "搞笑", "无");
		Riddle riddle267 = new Riddle("否(打一教育用语)", " 不及格", "搞笑", "无");
		Riddle riddle268 = new Riddle("二八佳人  (猜一个字)", " 妙", "搞笑", "无");
		Riddle riddle269 = new Riddle("君王从此不早朝(打一食品名)", " 艾窝窝", "搞笑", "无");
		Riddle riddle270 = new Riddle("三个金鑫，三个水叫淼，三个人叫众，那么三个鬼应该叫什么?(猜一猜)",
				" 叫救命", "搞笑", "无");
		Riddle riddle271 = new Riddle("一个太监(打一水果)", " 梅子(没子：没有儿子)", "搞笑", "无");
		Riddle riddle272 = new Riddle("星姐选举(打一国家)", " 以色列", "搞笑", "无");
		Riddle riddle273 = new Riddle("禁止放牲口出去吃草(打一诗人)", " 杜牧", "搞笑", "无");
		Riddle riddle274 = new Riddle("喝三口水解酸(打一饮品)", " 酒", "搞笑", "无");
		Riddle riddle275 = new Riddle("小飞贼，水里生，干坏事，狠又凶，偷偷摸摸吸人血，还要嗡嗡叫几声。(打一动物)",
				" 蚊子", "搞笑", "无");
		Riddle riddle276 = new Riddle("火辣辣的手(打一花卉)", " 红掌(别称“火鹤花、安祖花”)", "搞笑",
				"无");
		Riddle riddle277 = new Riddle("号称山大王，树干冲天长，叶儿尖似针，造屋好做梁。(打一植物)", " 杉树",
				"搞笑", "无");
		Riddle riddle278 = new Riddle("天南地北都能住，春风给我把辫梳，溪畔湖旁搭凉棚，能撒雪花当空舞。(打一植物)",
				" 柳树", "搞笑", "无");
		Riddle riddle279 = new Riddle("双胞胎老大(打一称呼)", " 先生", "搞笑", "无");
		Riddle riddle280 = new Riddle("琵琶舞(打一体育用语)", " 弹跳", "搞笑", "无");
		Riddle riddle281 = new Riddle("袁珊珊票房赢了杨幂(打一热门人物)", " 袁厉害", "搞笑", "无");
		Riddle riddle282 = new Riddle("走进死巷子(打一物理名词)", " 断路", "搞笑", "无");
		Riddle riddle283 = new Riddle("冻猪头(打一食品名)", " 冷狗", "搞笑", "无");
		Riddle riddle284 = new Riddle("习惯性流产(打一称谓)", " 老先生", "搞笑", "无");
		Riddle riddle285 = new Riddle("瞎丑(打一乳制品品牌)", " 蒙牛", "搞笑", "无");
		Riddle riddle286 = new Riddle("一只手能干的事情(打一热门小说)", " 《遮天》(因为“只手遮天”)",
				"搞笑", "无");
		Riddle riddle287 = new Riddle("貌赛潘安(打一港台男明星)", " 潘玮柏", "搞笑", "无");
		Riddle riddle288 = new Riddle("十二点  (猜一个字)", " 斗", "搞笑", "无");
		Riddle riddle289 = new Riddle("四根头发掉了一根(打一作家)", " 三毛", "搞笑", "无");
		Riddle riddle290 = new Riddle("天地会(打一出版用语)", " 上下集", "搞笑", "无");
		Riddle riddle291 = new Riddle("男人也能怀孕(打一电影)", " 《肚爸爸生子记》", "搞笑", "无");
		Riddle riddle292 = new Riddle("死在地里的小红薯(打一离合字)", " 土里埋", "搞笑", "无");
		Riddle riddle293 = new Riddle("兜售小三(打一美国城市)", " 迈阿密(卖，阿密：小三(小秘))",
				"搞笑", "无");
		Riddle riddle294 = new Riddle("你知道最有价值的布是什么吗？(猜一猜)", " 瀑布", "搞笑", "无");
		Riddle riddle295 = new Riddle("用被子盖住屁股(打一个城市名字)", " 保定(抱定 ，定徐州话屁股)",
				"搞笑", "无");
		Riddle riddle296 = new Riddle("手捧一把棍(打一复姓)", " 端木", "搞笑", "无");
		Riddle riddle297 = new Riddle("把尿喝光不撑肚，妈妈有它少烦恼(打一生活用品)", " 尿不湿", "搞笑",
				"无");
		Riddle riddle298 = new Riddle("什么时候，时代广场的大钟会响13下？(猜一猜)", " 该修理的时候",
				"搞笑", "无");
		Riddle riddle299 = new Riddle("宾客尽脱帽，洒泪来反思(打一音乐人，2字)", " 洛兵", "搞笑", "无");
		Riddle riddle300 = new Riddle("二师兄的嗓门(打一明星)", " 朱茵(猪音)", "搞笑", "无");
		Riddle riddle301 = new Riddle("八戒头上一滩血(打一花卉)", " 朱顶红(猪顶红)", "搞笑", "无");
		Riddle riddle302 = new Riddle(
				"百货公司里，有个秃头的推销员，正在促销生发水，你知道他为什么自己不用生发水呢？(猜一猜)",
				" 他想让大家知道秃头有多难看", "搞笑", "无");
		Riddle riddle303 = new Riddle("鸡蛋(打一歌名)", " 心太软", "搞笑", "无");
		Riddle riddle304 = new Riddle("月(打一教育用语)", " 期末", "搞笑", "无");
		Riddle riddle305 = new Riddle("满足(打一物)", " 袜子", "搞笑", "无");
		Riddle riddle306 = new Riddle("鼠年出生(打一人名)", " 子产", "搞笑", "无");
		Riddle riddle307 = new Riddle("女人穿裤子(打一车名)", " 蓝(拦)鸟", "搞笑", "无");
		Riddle riddle308 = new Riddle("都来太阳底下穿袄子(打一物理名词)", " 比热", "搞笑", "无");
		Riddle riddle309 = new Riddle("你无知到似母猪(打一明星)", " 李宇春", "搞笑", "无");
		Riddle riddle310 = new Riddle("电世界(打一食品)", " 麻球", "搞笑", "无");
		Riddle riddle311 = new Riddle("黑色的车(打一名词)", " 黑车", "搞笑", "无");
		Riddle riddle312 = new Riddle("嫦娥在哪里(打一歌名)", " 月亮之上", "搞笑", "无");
		Riddle riddle313 = new Riddle("一架坐满人的飞机坠落，却没有人受伤。为什么？(猜一猜)", " 因为都死了",
				"搞笑", "无");
		Riddle riddle314 = new Riddle("海南宝岛是我家，不怕风吹和雨打，四季棉衣不离身，肚里有肉又有茶。(打一植物)",
				" 椰子", "搞笑", "无");
		Riddle riddle315 = new Riddle("男女关系不清不白(打一歌曲)", " 《暧昧》——杨丞琳", "搞笑", "无");
		Riddle riddle316 = new Riddle("曲从其命(打一曲艺用语)", " 绕口令", "搞笑", "无");
		Riddle riddle317 = new Riddle("你在什么时候喜欢喝汽水？(猜一猜)",
				" 在孤单的时候(当你孤单你会想汽水)", "搞笑", "无");
		Riddle riddle318 = new Riddle("在家千般好(打一人事用语)", " 出差", "搞笑", "无");
		Riddle riddle319 = new Riddle("30岁的女人(打一成语)", " 如狼似虎", "搞笑", "无");
		Riddle riddle320 = new Riddle("一个太监(打一水果)", " 梅子(没子：没有儿子)", "搞笑", "无");

		Riddle riddle321 = new Riddle("纸绢糊在竹篾上，飞上天空微荡漾(打一玩具)", " 风筝", "儿童", "无");
		Riddle riddle322 = new Riddle("外披麻衣裟，内穿红大褂。胖子里面藏，地下自安家(打一农作物)", " 花生",
				"儿童", "无");
		Riddle riddle323 = new Riddle("被马来西亚视为不吉祥的花(打一花卉)", " 蝙蝠花(因为不讨喜的外型)",
				"儿童", "无");
		Riddle riddle324 = new Riddle("穿奇装异服的人最让谁头疼？(猜一猜)", " 裁缝", "儿童", "无");
		Riddle riddle325 = new Riddle("儿童节合唱(打一离合字)", " 六一日音", "儿童", "无");
		Riddle riddle326 = new Riddle("儿童节(打一离合字)", " 自小省", "儿童", "无");
		Riddle riddle327 = new Riddle("外表似荔枝，却带长软刺(打一水果)", " 红毛丹", "儿童", "无");
		Riddle riddle328 = new Riddle("八十万禁军教头，人称豹子头(打一水浒传人物名)", " 林冲", "儿童",
				"无");
		Riddle riddle329 = new Riddle("我有一个好帮手，书本文具帮我拿，每天跟我上学校(打一物)", " 书包",
				"儿童", "无");
		Riddle riddle330 = new Riddle("长生果儿穿麻衣，植物肉儿白身体(打一食物)", " 花生", "儿童", "无");
		Riddle riddle331 = new Riddle("样子像小船，角儿两头翘，骨头在外面，肉儿里头包。(打一植物)", " 菱角",
				"儿童", "无");
		Riddle riddle332 = new Riddle("一根根，两成双，三个指头夹住它(打一生活用品)", " 筷子", "儿童",
				"无");
		Riddle riddle333 = new Riddle("头上毛发像大便，好吃懒做大肥羊。(打一动画角色)", " 懒羊羊", "儿童",
				"无");
		Riddle riddle334 = new Riddle("调皮小萝莉(打一网站名)", " 淘宝网(淘气的宝宝)", "儿童", "无");
		Riddle riddle335 = new Riddle("八仙过海到此地，伏羲女娲居其中(打一地名)", " 蓬莱(烟台县级市)",
				"儿童", "无");
		Riddle riddle336 = new Riddle("一堆黄豆豆,磨碎熬白汤,凝成大块块,吃来嫩又香(打一食品)", " 豆腐",
				"儿童", "无");
		Riddle riddle337 = new Riddle("两只小小船，陪你在身边，白天到处走，晚上停床边(打一日用品)", " 鞋",
				"儿童", "无");
		Riddle riddle338 = new Riddle("多出一半(打一个字)", " 岁", "儿童", "无");
		Riddle riddle339 = new Riddle("有个大肚皮,真是怪脾气,张着大嘴街上站,专吃各种脏东西(打一日用品)",
				" 垃圾桶", "儿童", "无");
		Riddle riddle340 = new Riddle("鱼为什么生活在水里？(猜一猜)", " 因为猫在岸上", "儿童", "无");
		Riddle riddle341 = new Riddle("方方一块布,布上长疙瘩，天天去洗脸，说我好娃娃(打一日用品)", " 毛巾",
				"儿童", "无");
		Riddle riddle342 = new Riddle("鹿儿雪橇响叮当，小小礼物娃娃抢(打一国外节日)", " 圣诞节", "儿童",
				"无");
		Riddle riddle343 = new Riddle("灯暗秋寒明月隐(打一花名)", " 丁香", "儿童", "无");
		Riddle riddle344 = new Riddle("湿、绿、岸、春、透组成一句话，哈哈(猜一猜)",
				" 岸湿透春绿，俺是头蠢驴！", "儿童", "无");
		Riddle riddle345 = new Riddle("小明考了96分小华比小明多一点小华考了多少分(猜一猜)", " 9.6分",
				"儿童", "无");
		Riddle riddle346 = new Riddle(
				"比牛大，吃嫩草，头上长只弯弯角。老虎见它让三分，力大无穷脾气暴。( 打一动物)", " 犀牛", "儿童", "无");
		Riddle riddle347 = new Riddle("身细头尖鼻子大，一根线儿拴住它， 帮助妈妈缝衣裳，帮助姐姐来绣花。(猜一猜)",
				" 针", "儿童", "无");
		Riddle riddle348 = new Riddle("远看像雪花，海水中有它。用途非常广，做菜顶呱呱(打一调味品)", " 盐",
				"儿童", "无");
		Riddle riddle349 = new Riddle("头裹布巾的小女孩(打一儿歌)", " 《小红帽》", "儿童", "无");
		Riddle riddle350 = new Riddle("红皮红瓤瓤,酸甜有营养,生吃赛水果,蛋炒味道香(打一蔬菜)", " 西红柿",
				"儿童", "无");
		Riddle riddle351 = new Riddle("半推半就 (猜一字)", " 掠", "儿童", "无");
		Riddle riddle352 = new Riddle("七个男孩智斗美人儿(打一动画片)", " 《葫芦娃》", "儿童", "无");
		Riddle riddle353 = new Riddle("一点一横长，方砖顶房梁。大口张着嘴，小口里面藏(猜一猜)", " 高",
				"儿童", "无");
		Riddle riddle354 = new Riddle("死去何所知打酒名一(猜一猜)", " 百年糊涂", "儿童", "无");
		Riddle riddle355 = new Riddle("远看像张弓，立在河当中。上面车马过，底下轮船游。(打一交通设施)", " 桥",
				"儿童", "无");
		Riddle riddle356 = new Riddle("戴绿帽披绿衫,白天黑夜邮局站,书信往来它传递，准确及时又安全(打一物品)",
				" 邮筒", "儿童", "无");
		Riddle riddle357 = new Riddle(
				"默默站在马路边,没到夜晚亮闪闪,汽车行人从旁过，尽职尽责保安全(打一交通设施)", " 路灯", "儿童", "无");
		Riddle riddle358 = new Riddle("轻轻跑过水面,水面浪花飞溅,树叶哗哗鼓掌,响声连成一片(打一自然现象)",
				" 风", "儿童", "无");
		Riddle riddle359 = new Riddle("身披黄袍营养高，热带果王味道好(打一水果)", " 芒果", "儿童", "无");
		Riddle riddle360 = new Riddle("小时开白花,大时结黄果,皮粗果肉细,吃了能败火(打一水果)", " 梨",
				"儿童", "无");
		Riddle riddle361 = new Riddle("一只像耳插在后，一条象鼻长在前，肚子像孕妇，有水往里灌", " 水壶",
				"儿童", "无");
		Riddle riddle362 = new Riddle("木头上面来回走,光有牙,没有口(打一物)", " 锯子", "儿童", "无");
		Riddle riddle363 = new Riddle("冬天蟠龙卧，夏天枝叶开，龙须往上长，珍珠往下排。(打一植物)", " 葡萄",
				"儿童", "无");
		Riddle riddle364 = new Riddle("一个老爷爷，推他他不倒，总爱把头摇(打一物)", " 不倒翁", "儿童",
				"无");
		Riddle riddle365 = new Riddle("壳儿硬，门板隔，里面藏个皱脸老婆婆(打一坚果)", " 核桃", "儿童",
				"无");
		Riddle riddle366 = new Riddle("平安的果子 (打一水果)", " 苹果", "儿童", "无");
		Riddle riddle367 = new Riddle(
				"小小银针本领高，扎了脑袋扎双脚。外国朋友也欢迎，针到病除乐陶陶。(打一治病方法)", " 针灸", "儿童", "无");
		Riddle riddle368 = new Riddle("四四方方像块糖，写错字儿用它擦(打一学习用品)", " 橡皮擦", "儿童",
				"无");
		Riddle riddle369 = new Riddle("身材细又长，头上长着毛，每天把它用，做个好宝宝(打一日用品)", " 牙刷",
				"儿童", "无");
		Riddle riddle370 = new Riddle("弄瓦之徵  (猜一个字)", " 姚", "儿童", "无");
		Riddle riddle371 = new Riddle("五人住一起，个头也不齐(打一人体部位)", " 手指", "儿童", "无");
		Riddle riddle372 = new Riddle("那拔(打一水果)", " 番石榴(俗称“那拔”)", "儿童", "无");
		Riddle riddle373 = new Riddle("四四方方豆腐块,每天早起亲脸蛋(打一物)", " 毛巾", "儿童", "无");
		Riddle riddle374 = new Riddle("吃进青青草,挤出甜甜水,谢谢牛妈妈,让我快长大(打一食品)", " 牛奶",
				"儿童", "无");
		Riddle riddle375 = new Riddle("身材纤细披绿袍，切碎包饺味儿好(打一蔬菜)", " 芹菜", "儿童", "无");
		Riddle riddle376 = new Riddle("圆圆浆果色泽艳，草龙珠儿味道甜(打一水果)", " 葡萄", "儿童", "无");
		Riddle riddle377 = new Riddle("小花儿，像喇叭，公鸡打鸣就开放(打一花类)", " 牵牛花", "儿童",
				"无");
		Riddle riddle378 = new Riddle("一座小楼房,有门没有窗。打开看一看,全是花衣裳(打一家具)", " 衣柜",
				"儿童", "无");
		Riddle riddle379 = new Riddle("一个绿娃娃，肚里水汪汪，若是剖开看，红汁往外淌(打一水果)", " 西瓜",
				"儿童", "无");
		Riddle riddle380 = new Riddle("没有孩子却当娘，欢欢喜喜红线牵。 (打一角色)", " 红娘", "儿童",
				"无");
		Riddle riddle381 = new Riddle("小小哥儿鼻子长，肚子还能把水装。它见花儿流眼泪，花儿见它笑哈哈。(打一物)",
				" 洒水壶", "儿童", "无");
		Riddle riddle382 = new Riddle("一头青来一头白，胡子还在地里埋。头上顶朵小白花，烹饪调味品质佳。(打一蔬菜)",
				" 葱", "儿童", "无");
		Riddle riddle383 = new Riddle("毛茸茸像羽毛，大风一吹到处飞(打一植物)", " 蒲公英", "儿童", "无");
		Riddle riddle384 = new Riddle("别看不会说话,无脚能行千里,先报一路平安,话都装在肚里(打一日用品)",
				" 信", "儿童", "无");
		Riddle riddle385 = new Riddle("白雪公主出自那本书(猜一猜)", " 格林童话", "儿童", "无");
		Riddle riddle386 = new Riddle("在中国被人唱的最多的歌是什么歌？(猜一猜)", " 国歌", "儿童", "无");
		Riddle riddle387 = new Riddle("五颜六色真漂亮，喜庆时节都用它(打一玩具)", " 气球", "儿童", "无");
		Riddle riddle388 = new Riddle("魔法少女的珍贵友情(打一动画片)", " 《彩虹心石》", "儿童", "无");
		Riddle riddle389 = new Riddle("小兰并没有病，为什么她吃一口吐一口？", " 她在吃瓜子", "儿童", "无");
		Riddle riddle390 = new Riddle("披黄衣，不用剥，酸眯眼，泡泡水(打一水果)", " 柠檬", "儿童", "无");
		Riddle riddle391 = new Riddle("胖娃娃，嘴真大，不管吃啥都要它(打一生活用品)", " 碗", "儿童",
				"无");
		Riddle riddle392 = new Riddle("就怕热不怕冷,像玻璃亮晶晶,数九寒天河里有,春暖花开难找寻(打一自然物)",
				" 冰", "儿童", "无");
		Riddle riddle393 = new Riddle("热带果王(打一水果)", " 芒果", "儿童", "无");
		Riddle riddle394 = new Riddle("丘比特之剑 -的一中药", " 穿心莲。了(箭把两颗心穿透连在一起)",
				"儿童", "无");
		Riddle riddle395 = new Riddle("嘴巴大又大,从来不讲话。盛汤又装饭,落地会摔烂(打一物)", " 碗",
				"儿童", "无");
		Riddle riddle396 = new Riddle("人脱衣服，它穿衣服，人脱帽子，它戴帽子(打一物品)", " 衣帽架",
				"儿童", "无");
		Riddle riddle397 = new Riddle("洁白如玉有大口，装菜盛饭好帮手(打一生活用品)", " 碗", "儿童",
				"无");
		Riddle riddle398 = new Riddle(
				"一匹马儿两人骑，这边高来那边低， 虽然马儿不会跑，两人骑着笑嘻嘻。 (打一玩具)", " 跷跷板", "儿童", "无");
		Riddle riddle399 = new Riddle(
				"IQ博士智商高，机器娃娃也来造；近视娃娃个头小，最爱说着哦呦呦(打一动漫人物)", " 阿拉蕾", "儿童", "无");
		Riddle riddle400 = new Riddle("你有两只眼,它有两只眼,剧场来看戏,它能看得远(打一科技产品)",
				" 望远镜", "儿童", "无");

		Riddle riddle401 = new Riddle("卡罗拉(打一英文单词)", " corolla花冠", "英语", "无");
		Riddle riddle402 = new Riddle("怕失去(打一英文单词)", " parsimonious", "英语", "无");
		Riddle riddle403 = new Riddle(
				"What has hands but no feet, a face but no eyes, tells but not talk? (猜一猜)",
				" alarm clock 闹钟", "英语", "无");
		Riddle riddle404 = new Riddle("台湾姐妹明星(打一英文字)", " s", "英语", "无");
		Riddle riddle405 = new Riddle(
				"Why don't you advertise for your lost dog?(猜一猜)",
				" He can't read.", "英语", "无");
		Riddle riddle406 = new Riddle(
				"Why is the library the highest building?(猜一猜)",
				" It has the most stories.", "英语", "无");
		Riddle riddle407 = new Riddle(
				"When the boy fell into the water, what's the first thing he did? (猜一猜)",
				" He got wet first of all.", "英语", "无");
		Riddle riddle408 = new Riddle("什么英文字母让人们喜欢听而且听的人最多？(猜一猜)", " CD", "英语",
				"无");
		Riddle riddle409 = new Riddle("话说26个字母在外太空，ABC跑地球来了，外太空还有多少个字母 (猜一猜)",
				" 20个，他们坐UFO下来的！", "英语", "无");
		Riddle riddle410 = new Riddle(
				"What animal wears big black glasses on its face?(猜一猜)",
				" Panda熊猫", "英语", "无");
		Riddle riddle411 = new Riddle("阴塞(打一英语单词)", " inside", "英语", "无");
		Riddle riddle412 = new Riddle(
				"What always goes up and never goes down?(什么东西只升不降？)",
				" Your age. (你的年龄)", "英语", "无");
		Riddle riddle413 = new Riddle("摸(打一英文单词)", " more(多，更多)", "英语", "无");
		Riddle riddle414 = new Riddle("C型的锁 (打一英文)", " clock.", "英语", "无");
		Riddle riddle415 = new Riddle(
				"How long can a goose stand on one leg？(多久能鹅单腿站立？)",
				" try it and see", "英语", "无");
		Riddle riddle416 = new Riddle("what month do soldiers hate(猜一猜)",
				" March 3月", "英语", "无");
		Riddle riddle417 = new Riddle("俺靠(打一英文单词)", " uncle叔叔；伯父；舅父；姑父，姨父。",
				"英语", "无");
		Riddle riddle418 = new Riddle(
				"What kind of water should people drink in order to be healthy? (猜一猜)",
				" Drink well water.", "英语", "无");
		Riddle riddle419 = new Riddle("狗打毛宁(打一英文短语)", " Good morning!早上好！",
				"英语", "无");
		Riddle riddle420 = new Riddle(
				"Why did the farmer take his chicken to task?(农夫为什么训斥小鸡？)",
				" Because they use foul language. 因为它们说脏话。", "英语", "无");
		Riddle riddle421 = new Riddle("一窝蚊子(打一英语单词)", " involving", "英语", "无");
		Riddle riddle422 = new Riddle("What has teeth but cannot eat?(猜一猜)",
				" A comb", "英语", "无");
		Riddle riddle423 = new Riddle(
				"Why do some old people never use glasses? (猜一猜)",
				" They must prefer bottles to glasses.", "英语", "无");
		Riddle riddle424 = new Riddle(
				"What has four eyes but cannot see?(猜一猜)", " 密西西比州", "英语", "无");
		Riddle riddle425 = new Riddle("海味(打一英文单词)", " heavy", "英语", "无");
		Riddle riddle426 = new Riddle("皮蛋(打一英文)", " Q", "英语", "无");
		Riddle riddle427 = new Riddle(
				"Why is the inside of everything so mysterious?(为什么说凡事内部都神秘莫测？)",
				" ecause you can never make them out. 因为你永远也不能弄懂它们。", "英语", "无");
		Riddle riddle428 = new Riddle("迪斯科(打一英文单词)", " disco", "英语", "无");
		Riddle riddle429 = new Riddle("What comes after the letter 'A'?(猜一猜)",
				" All the other letters.", "英语", "无");
		Riddle riddle430 = new Riddle("216个小时打一个字", " 旭(因为216小时是九日)", "英语", "无");
		Riddle riddle431 = new Riddle("堤坝(打一英文单词)", " debar", "英语", "无");
		Riddle riddle432 = new Riddle("What part of London is in Brazil?(猜一猜)",
				" The letter 'L'.", "英语", "无");
		Riddle riddle433 = new Riddle("为立志(打一英文单词)", " village村民；乡村，村庄；群落。",
				"英语", "无");
		Riddle riddle434 = new Riddle("卖几颗(打一英文单词)", " magic(魔法，魔术)", "英语", "无");
		Riddle riddle435 = new Riddle("赖(打一英文单词)", " lie", "英语", "无");
		Riddle riddle436 = new Riddle(
				"What question can you never answer 'Yes' to?(猜一猜)",
				" Are you dead?", "英语", "无");
		Riddle riddle437 = new Riddle("我的吻车(打一英文单词)",
				" adventure冒险活动；冒险经历；奇遇。", "英语", "无");
		Riddle riddle438 = new Riddle("二奶(打一英文单词)", " annoy使烦恼；令人讨厌；烦恼", "英语",
				"无");
		Riddle riddle439 = new Riddle("Why do people go to bed?(人们为什么睡觉？)",
				" Because the bed won’t come to us．因为床不会走向我们。", "英语", "无");
		Riddle riddle440 = new Riddle("逮狸射死(打一英文单词)", " delicious", "英语", "无");
		Riddle riddle441 = new Riddle("摸钱袋子(打一英文单词)", " merchandise", "英语", "无");
		Riddle riddle442 = new Riddle(
				"What's the difference between a hill and a pill? (猜一猜)",
				" A hill is hard to get up and a pill is hard to get down",
				"英语", "无");
		Riddle riddle443 = new Riddle(
				"What is deaf and dumb but always tells the truth? (打一物)",
				" mirror", "英语", "无");
		Riddle riddle444 = new Riddle(
				"What word can you make shorter by adding to it?(猜一猜)",
				" Short.", "英语", "无");
		Riddle riddle445 = new Riddle("阿匹婆(打一英文名词)", " a people", "英语", "无");
		Riddle riddle446 = new Riddle("死壮(打一英文单词)", " strong强壮", "英语", "无");
		Riddle riddle447 = new Riddle(
				"Ten plus ten is ten.Ten minus ten is ten(打一物)", " 手套(glove)",
				"英语", "无");
		Riddle riddle448 = new Riddle(
				"Where did Columbus stand when he discovered America?(猜一猜)",
				" On his feet.", "英语", "无");
		Riddle riddle449 = new Riddle("得死(打一英文单词)", " dearth", "英语", "无");
		Riddle riddle450 = new Riddle("爷爷去世 (打一英文)", " yes.", "英语", "无");
		Riddle riddle451 = new Riddle("我有(打一英文单词)", " volume大量的", "英语", "无");
		Riddle riddle452 = new Riddle("柴门(打一英文单词)", " chairman", "英语", "无");
		Riddle riddle453 = new Riddle("楷模(打一英文单词)", " camel骆驼", "英语", "无");
		Riddle riddle454 = new Riddle("嬉戏(打一英文单词)", " cease", "英语", "无");
		Riddle riddle455 = new Riddle("想念的时候，已经是失去 (打一英文)", " miss.", "英语", "无");
		Riddle riddle456 = new Riddle("传飞客(打一英文单词)", " traffic", "英语", "无");
		Riddle riddle457 = new Riddle("故障女士 (打一动物)", " lady bug(瓢虫)。", "英语",
				"无");
		Riddle riddle458 = new Riddle("有没有你，颜色都不变 (打一英文)", " colour/color",
				"英语", "无");
		Riddle riddle459 = new Riddle(
				"What never asks questions but gets a lot of answers?(猜一猜)",
				" dictionary", "英语", "无");
		Riddle riddle460 = new Riddle("俺必胜(打一英文单词)", " ambition雄心", "英语", "无");
		Riddle riddle461 = new Riddle("蔻驰(打一英文单词)", " 教练；指导；受训练。", "英语", "无");
		Riddle riddle462 = new Riddle(
				"What is that which has a mouth,but never speaks, and a bed, but never sleeps in it?(猜一猜)",
				" A river.", "英语", "无");
		Riddle riddle463 = new Riddle("额耐克(打一英语单词)", " alike(相同的，相像的 )", "英语",
				"无");
		Riddle riddle464 = new Riddle("我跑(打一英文单词)", " appall惊骇", "英语", "无");
		Riddle riddle465 = new Riddle(
				"What is never used until it's broken?(猜一猜)", " An egg", "英语",
				"无");
		Riddle riddle466 = new Riddle(
				"What can you break with only one word?(猜一猜)", " Silence.",
				"英语", "无");
		Riddle riddle467 = new Riddle(
				"How do we know the ocean is friendly?(猜一猜)", " It waves.",
				"英语", "无");
		Riddle riddle468 = new Riddle(
				"What stays indoors no matter how many times you put it out?(猜一猜)",
				" The light.", "英语", "无");
		Riddle riddle469 = new Riddle("Which horses have six legs?(猜一猜)",
				" All horses have forelegs", "英语", "无");
		Riddle riddle470 = new Riddle("不认识(打一英文单词)", " prince王子；巨头；", "英语", "无");
		Riddle riddle471 = new Riddle("X(打一哲学用语)", " 否定判断", "英语", "无");
		Riddle riddle472 = new Riddle("这破妮子(打一英文单词)", " Japanese", "英语", "无");
		Riddle riddle473 = new Riddle("what letter is an animal?(打一英文字母)",
				" B", "英语", "无");
		Riddle riddle474 = new Riddle("潲哇(打一英文单词)",
				" shower阵雨；淋浴；淋浴器；一大批；下阵雨；似阵雨般降落；洒落；纷纷降落。", "英语", "无");
		Riddle riddle475 = new Riddle("死洞(打一英文单词)", " stone(石头、宝石)", "英语", "无");
		Riddle riddle476 = new Riddle(
				"How does the sun affect weight？(太阳如何影响重量？)",
				" It makes the daylight.", "英语", "无");
		Riddle riddle477 = new Riddle("爱过你(打一英文单词)", " agony痛苦", "英语", "无");
		Riddle riddle478 = new Riddle(
				"Why can a bride hide nothing?(为什么新娘子什么也藏不住？)",
				" Because someone will give her away.", "英语", "无");
		Riddle riddle479 = new Riddle("老爷(打一英文单词)", " lawyer律师，法学家", "英语", "无");
		Riddle riddle480 = new Riddle("冷(打一英文单词)", " learn", "英语", "无");

		Riddle riddle481 = new Riddle("街边行走,解闷散心(打一字)", " 闺", "字谜", "无");
		Riddle riddle482 = new Riddle("有儿有女日子美(打一字)", " 好", "字谜", "无");
		Riddle riddle483 = new Riddle("走了魔头,跑了小鬼(打一字)", " 林", "字谜", "无");
		Riddle riddle484 = new Riddle("人家帘幕垂(打一字)", " 扉", "字谜", "无");
		Riddle riddle485 = new Riddle("人到画中游(打一字)", " 佃", "字谜", "无");
		Riddle riddle486 = new Riddle("扁舟归去(打一字)", " 迥", "字谜", "无");
		Riddle riddle487 = new Riddle("脱贫之后心相依(打一字)", " 岔", "字谜", "无");
		Riddle riddle488 = new Riddle("这一页(打一字)", " 题(这=是)", "字谜", "无");
		Riddle riddle489 = new Riddle("长长的毛巾 (打一字)", " 帐", "字谜", "无");
		Riddle riddle490 = new Riddle("失水即可(打一字)", " 河", "字谜", "无");
		Riddle riddle491 = new Riddle("停在月亮脑袋上(打一字)", " 肯", "字谜", "无");
		Riddle riddle492 = new Riddle("一手撑破天(打一字)", " 扶", "字谜", "无");
		Riddle riddle493 = new Riddle("每逢佳节(打一字)", " 侮", "字谜", "无");
		Riddle riddle494 = new Riddle("石头下有水(打一字)", " 泵", "字谜", "无");
		Riddle riddle495 = new Riddle("楼头相会两倾心(打一字)", " 枇", "字谜", "无");
		Riddle riddle496 = new Riddle("用心改革鼎力相助(打一字)", " 荔", "字谜", "无");
		Riddle riddle497 = new Riddle("南边一条狗，要牵它一起走。(打一字)", " 献。", "字谜", "无");
		Riddle riddle498 = new Riddle("我困在一个密室里(打一字)", " 圄(“吾”寓意“我”)", "字谜",
				"无");
		Riddle riddle499 = new Riddle("二月(打一字)", " 朋", "字谜", "无");
		Riddle riddle500 = new Riddle("一箭射日(打一字)", " 申", "字谜", "无");
		Riddle riddle501 = new Riddle("生产须要配合(打一字)", " 颜", "字谜", "无");
		Riddle riddle502 = new Riddle("一个人过七夕(打一字)", " 死(“一”+“七”+“夕”=“死’)",
				"字谜", "无");
		Riddle riddle503 = new Riddle("干活尚需自留心(打一字谜)", " 憩", "字谜", "无");
		Riddle riddle504 = new Riddle("湖水干涸月影无(打一字)", " 古", "字谜", "无");
		Riddle riddle505 = new Riddle("山上有一兵，断了两条腿(打一字)", " 岳", "字谜", "无");
		Riddle riddle506 = new Riddle("鼓声招来八方人(打一字)", " 谷", "字谜", "无");
		Riddle riddle507 = new Riddle("一百减一  (打一字)", " 白", "字谜", "无");
		Riddle riddle508 = new Riddle("不是门也是门 (打一字)", " 扉", "字谜", "无");
		Riddle riddle509 = new Riddle("买前重点选择(打一字)", " 实", "字谜", "无");
		Riddle riddle510 = new Riddle("一笔不直,二笔不曲,三笔有直有曲,数量单位却很大.(打一字)", " 亿",
				"字谜", "无");
		Riddle riddle511 = new Riddle("下上一点(打一字)", " 卞", "字谜", "无");
		Riddle riddle512 = new Riddle("站在狗旁边的人(打一字)", " 伏", "字谜", "无");
		Riddle riddle513 = new Riddle("水木火土舍前二(打一字)", " 灻", "字谜", "无");
		Riddle riddle514 = new Riddle("最合心意的字(打一字)", " 恰", "字谜", "无");
		Riddle riddle515 = new Riddle("纵横交错(打一字)", " 十", "字谜", "无");
		Riddle riddle516 = new Riddle("夫妻同心(打一字)", " 怂", "字谜", "无");
		Riddle riddle517 = new Riddle("一口马打一字", " 吗", "字谜", "无");
		Riddle riddle518 = new Riddle("路旁无足迹(打一字)", " 各", "字谜", "无");
		Riddle riddle519 = new Riddle("没有右耳的人 (打一字)", " 队(只有左耳=左耳旁)", "字谜", "无");
		Riddle riddle520 = new Riddle("无可奈何大小去(打一字)", " 仁", "字谜", "无");
		Riddle riddle521 = new Riddle("一个人，四个叉 (打一字)", " 爽", "字谜", "无");
		Riddle riddle522 = new Riddle("双手两边放(打一字)", " 掰", "字谜", "无");
		Riddle riddle523 = new Riddle("受戒十八年 打一字", " 械", "字谜", "无");
		Riddle riddle524 = new Riddle("目送倩人去(打一字)", " 睛", "字谜", "无");
		Riddle riddle525 = new Riddle("一吼方休(打一字)", " 孔", "字谜", "无");
		Riddle riddle526 = new Riddle("苦中十移位(打一字)", " 苗", "字谜", "无");
		Riddle riddle527 = new Riddle("欲医相思少偏方(打一字)", " 短", "字谜", "无");
		Riddle riddle528 = new Riddle("又小又大的是什么？(打一字)", " 尖", "字谜", "无");
		Riddle riddle529 = new Riddle("添子固然好还是少生妙(打一字)", " 女", "字谜", "无");
		Riddle riddle530 = new Riddle("张口结舌(打一字)", " 千", "字谜", "无");
		Riddle riddle531 = new Riddle("一心控制人口(打一字)", " 恰", "字谜", "无");
		Riddle riddle532 = new Riddle("下落不明心牵挂(打一字)", " 芯", "字谜", "无");
		Riddle riddle533 = new Riddle("不走(打一字)", " 还", "字谜", "无");
		Riddle riddle534 = new Riddle("T从口入(打一字)", " 曱yuē(取物)", "字谜", "无");
		Riddle riddle535 = new Riddle("四木合一(打一字)", " 木", "字谜", "无");
		Riddle riddle536 = new Riddle("秋天的鱼(打一字)", " 鳅", "字谜", "无");
		Riddle riddle537 = new Riddle("又到双休人出游(打一字)", " 桑", "字谜", "无");
		Riddle riddle538 = new Riddle("屋内的一方桌 (打一字)", " 房", "字谜", "无");
		Riddle riddle539 = new Riddle("流泪出洋相(打一字)", " 样", "字谜", "无");
		Riddle riddle540 = new Riddle("永恒的太阳(打一字)", " 昶", "字谜", "无");
		Riddle riddle541 = new Riddle("与火共舞(打一字)", " 烘", "字谜", "无");
		Riddle riddle542 = new Riddle("东海降雨(打一字)", " 霉", "字谜", "无");
		Riddle riddle543 = new Riddle("岗位调动(打一字)", " 岖", "字谜", "无");
		Riddle riddle544 = new Riddle("六十天(打一字)", " 朋友(解释二个月)", "字谜", "无");
		Riddle riddle545 = new Riddle("地没有地(打一字)", " 也", "字谜", "无");
		Riddle riddle546 = new Riddle("上大下小合为一，二字一去不归来(打一字)", " 夵", "字谜", "无");
		Riddle riddle547 = new Riddle("树没了心(打一字)", " 村", "字谜", "无");
		Riddle riddle548 = new Riddle("四根棍子横竖交叉(打一字)", " 井", "字谜", "无");
		Riddle riddle549 = new Riddle("足球先生(打一字)", " 呈", "字谜", "无");
		Riddle riddle550 = new Riddle("灾后安稳别着急(打一字)", " 秋", "字谜", "无");
		Riddle riddle551 = new Riddle("花开除草午后培(打一字)", " 华", "字谜", "无");
		Riddle riddle552 = new Riddle("喝水口倒干(打一字)", " 洁", "字谜", "无");
		Riddle riddle553 = new Riddle("别人的信手勿拆(打一字)", " 诉", "字谜", "无");
		Riddle riddle554 = new Riddle("毛遂自荐(打一字)", " 衙", "字谜", "无");
		Riddle riddle555 = new Riddle("女尸(打一字)", " 妄", "字谜", "无");
		Riddle riddle556 = new Riddle("两口子碰面就吵嘴(打一字)", " 马", "字谜", "无");
		Riddle riddle557 = new Riddle("秋收之后是赛一赛(打一字)", " 秕", "字谜", "无");
		Riddle riddle558 = new Riddle("口中有口(打一字)", " 回", "字谜", "无");
		Riddle riddle559 = new Riddle("晴天无日不开怀(打一字)", " 情", "字谜", "无");
		Riddle riddle560 = new Riddle("如今中国面貌改(打一字)", " 玲", "字谜", "无");

		dbRManager.insert(riddle1);
		dbRManager.insert(riddle2);
		dbRManager.insert(riddle3);
		dbRManager.insert(riddle4);
		dbRManager.insert(riddle5);
		dbRManager.insert(riddle6);
		dbRManager.insert(riddle7);
		dbRManager.insert(riddle8);
		dbRManager.insert(riddle9);
		dbRManager.insert(riddle10);
		dbRManager.insert(riddle11);
		dbRManager.insert(riddle12);
		dbRManager.insert(riddle13);
		dbRManager.insert(riddle14);
		dbRManager.insert(riddle15);
		dbRManager.insert(riddle16);
		dbRManager.insert(riddle17);
		dbRManager.insert(riddle18);
		dbRManager.insert(riddle19);
		dbRManager.insert(riddle20);
		dbRManager.insert(riddle21);
		dbRManager.insert(riddle22);
		dbRManager.insert(riddle23);
		dbRManager.insert(riddle24);
		dbRManager.insert(riddle25);
		dbRManager.insert(riddle26);
		dbRManager.insert(riddle27);
		dbRManager.insert(riddle28);
		dbRManager.insert(riddle29);
		dbRManager.insert(riddle30);
		dbRManager.insert(riddle31);
		dbRManager.insert(riddle32);
		dbRManager.insert(riddle33);
		dbRManager.insert(riddle34);
		dbRManager.insert(riddle35);
		dbRManager.insert(riddle36);
		dbRManager.insert(riddle37);
		dbRManager.insert(riddle38);
		dbRManager.insert(riddle39);
		dbRManager.insert(riddle40);
		dbRManager.insert(riddle41);
		dbRManager.insert(riddle42);
		dbRManager.insert(riddle43);
		dbRManager.insert(riddle44);
		dbRManager.insert(riddle45);
		dbRManager.insert(riddle46);
		dbRManager.insert(riddle47);
		dbRManager.insert(riddle48);
		dbRManager.insert(riddle49);
		dbRManager.insert(riddle50);
		dbRManager.insert(riddle51);
		dbRManager.insert(riddle52);
		dbRManager.insert(riddle53);
		dbRManager.insert(riddle54);
		dbRManager.insert(riddle55);
		dbRManager.insert(riddle56);
		dbRManager.insert(riddle57);
		dbRManager.insert(riddle58);
		dbRManager.insert(riddle59);
		dbRManager.insert(riddle60);
		dbRManager.insert(riddle61);
		dbRManager.insert(riddle62);
		dbRManager.insert(riddle63);
		dbRManager.insert(riddle64);
		dbRManager.insert(riddle65);
		dbRManager.insert(riddle66);
		dbRManager.insert(riddle67);
		dbRManager.insert(riddle68);
		dbRManager.insert(riddle69);
		dbRManager.insert(riddle70);
		dbRManager.insert(riddle71);
		dbRManager.insert(riddle72);
		dbRManager.insert(riddle73);
		dbRManager.insert(riddle74);
		dbRManager.insert(riddle75);
		dbRManager.insert(riddle76);
		dbRManager.insert(riddle77);
		dbRManager.insert(riddle78);
		dbRManager.insert(riddle79);
		dbRManager.insert(riddle80);
		dbRManager.insert(riddle81);
		dbRManager.insert(riddle82);
		dbRManager.insert(riddle83);
		dbRManager.insert(riddle84);
		dbRManager.insert(riddle85);
		dbRManager.insert(riddle86);
		dbRManager.insert(riddle87);
		dbRManager.insert(riddle88);
		dbRManager.insert(riddle89);
		dbRManager.insert(riddle90);
		dbRManager.insert(riddle91);
		dbRManager.insert(riddle92);
		dbRManager.insert(riddle93);
		dbRManager.insert(riddle94);
		dbRManager.insert(riddle95);
		dbRManager.insert(riddle96);
		dbRManager.insert(riddle97);
		dbRManager.insert(riddle98);
		dbRManager.insert(riddle99);
		dbRManager.insert(riddle100);
		dbRManager.insert(riddle101);
		dbRManager.insert(riddle102);
		dbRManager.insert(riddle103);
		dbRManager.insert(riddle104);
		dbRManager.insert(riddle105);
		dbRManager.insert(riddle106);
		dbRManager.insert(riddle107);
		dbRManager.insert(riddle108);
		dbRManager.insert(riddle109);
		dbRManager.insert(riddle110);
		dbRManager.insert(riddle111);
		dbRManager.insert(riddle112);
		dbRManager.insert(riddle113);
		dbRManager.insert(riddle114);
		dbRManager.insert(riddle115);
		dbRManager.insert(riddle116);
		dbRManager.insert(riddle117);
		dbRManager.insert(riddle118);
		dbRManager.insert(riddle119);
		dbRManager.insert(riddle120);
		dbRManager.insert(riddle121);
		dbRManager.insert(riddle122);
		dbRManager.insert(riddle123);
		dbRManager.insert(riddle124);
		dbRManager.insert(riddle125);
		dbRManager.insert(riddle126);
		dbRManager.insert(riddle127);
		dbRManager.insert(riddle128);
		dbRManager.insert(riddle129);
		dbRManager.insert(riddle130);
		dbRManager.insert(riddle131);
		dbRManager.insert(riddle132);
		dbRManager.insert(riddle133);
		dbRManager.insert(riddle134);
		dbRManager.insert(riddle135);
		dbRManager.insert(riddle136);
		dbRManager.insert(riddle137);
		dbRManager.insert(riddle138);
		dbRManager.insert(riddle139);
		dbRManager.insert(riddle140);
		dbRManager.insert(riddle141);
		dbRManager.insert(riddle142);
		dbRManager.insert(riddle143);
		dbRManager.insert(riddle144);
		dbRManager.insert(riddle145);
		dbRManager.insert(riddle146);
		dbRManager.insert(riddle147);
		dbRManager.insert(riddle148);
		dbRManager.insert(riddle149);
		dbRManager.insert(riddle150);
		dbRManager.insert(riddle151);
		dbRManager.insert(riddle152);
		dbRManager.insert(riddle153);
		dbRManager.insert(riddle154);
		dbRManager.insert(riddle155);
		dbRManager.insert(riddle156);
		dbRManager.insert(riddle157);
		dbRManager.insert(riddle158);
		dbRManager.insert(riddle159);
		dbRManager.insert(riddle160);
		dbRManager.insert(riddle161);
		dbRManager.insert(riddle162);
		dbRManager.insert(riddle163);
		dbRManager.insert(riddle164);
		dbRManager.insert(riddle165);
		dbRManager.insert(riddle166);
		dbRManager.insert(riddle167);
		dbRManager.insert(riddle168);
		dbRManager.insert(riddle169);
		dbRManager.insert(riddle170);
		dbRManager.insert(riddle171);
		dbRManager.insert(riddle172);
		dbRManager.insert(riddle173);
		dbRManager.insert(riddle174);
		dbRManager.insert(riddle175);
		dbRManager.insert(riddle176);
		dbRManager.insert(riddle177);
		dbRManager.insert(riddle178);
		dbRManager.insert(riddle179);
		dbRManager.insert(riddle180);
		dbRManager.insert(riddle181);
		dbRManager.insert(riddle182);
		dbRManager.insert(riddle183);
		dbRManager.insert(riddle184);
		dbRManager.insert(riddle185);
		dbRManager.insert(riddle186);
		dbRManager.insert(riddle187);
		dbRManager.insert(riddle188);
		dbRManager.insert(riddle189);
		dbRManager.insert(riddle190);
		dbRManager.insert(riddle191);
		dbRManager.insert(riddle192);
		dbRManager.insert(riddle193);
		dbRManager.insert(riddle194);
		dbRManager.insert(riddle195);
		dbRManager.insert(riddle196);
		dbRManager.insert(riddle197);
		dbRManager.insert(riddle198);
		dbRManager.insert(riddle199);
		dbRManager.insert(riddle200);
		dbRManager.insert(riddle201);
		dbRManager.insert(riddle202);
		dbRManager.insert(riddle203);
		dbRManager.insert(riddle204);
		dbRManager.insert(riddle205);
		dbRManager.insert(riddle206);
		dbRManager.insert(riddle207);
		dbRManager.insert(riddle208);
		dbRManager.insert(riddle209);
		dbRManager.insert(riddle210);
		dbRManager.insert(riddle211);
		dbRManager.insert(riddle212);
		dbRManager.insert(riddle213);
		dbRManager.insert(riddle214);
		dbRManager.insert(riddle215);
		dbRManager.insert(riddle216);
		dbRManager.insert(riddle217);
		dbRManager.insert(riddle218);
		dbRManager.insert(riddle219);
		dbRManager.insert(riddle220);
		dbRManager.insert(riddle221);
		dbRManager.insert(riddle222);
		dbRManager.insert(riddle223);
		dbRManager.insert(riddle224);
		dbRManager.insert(riddle225);
		dbRManager.insert(riddle226);
		dbRManager.insert(riddle227);
		dbRManager.insert(riddle228);
		dbRManager.insert(riddle229);
		dbRManager.insert(riddle230);
		dbRManager.insert(riddle231);
		dbRManager.insert(riddle232);
		dbRManager.insert(riddle233);
		dbRManager.insert(riddle234);
		dbRManager.insert(riddle235);
		dbRManager.insert(riddle236);
		dbRManager.insert(riddle237);
		dbRManager.insert(riddle238);
		dbRManager.insert(riddle239);
		dbRManager.insert(riddle240);
		dbRManager.insert(riddle241);
		dbRManager.insert(riddle242);
		dbRManager.insert(riddle243);
		dbRManager.insert(riddle244);
		dbRManager.insert(riddle245);
		dbRManager.insert(riddle246);
		dbRManager.insert(riddle247);
		dbRManager.insert(riddle248);
		dbRManager.insert(riddle249);
		dbRManager.insert(riddle250);
		dbRManager.insert(riddle251);
		dbRManager.insert(riddle252);
		dbRManager.insert(riddle253);
		dbRManager.insert(riddle254);
		dbRManager.insert(riddle255);
		dbRManager.insert(riddle256);
		dbRManager.insert(riddle257);
		dbRManager.insert(riddle258);
		dbRManager.insert(riddle259);
		dbRManager.insert(riddle260);
		dbRManager.insert(riddle261);
		dbRManager.insert(riddle262);
		dbRManager.insert(riddle263);
		dbRManager.insert(riddle264);
		dbRManager.insert(riddle265);
		dbRManager.insert(riddle266);
		dbRManager.insert(riddle267);
		dbRManager.insert(riddle268);
		dbRManager.insert(riddle269);
		dbRManager.insert(riddle270);
		dbRManager.insert(riddle271);
		dbRManager.insert(riddle272);
		dbRManager.insert(riddle273);
		dbRManager.insert(riddle274);
		dbRManager.insert(riddle275);
		dbRManager.insert(riddle276);
		dbRManager.insert(riddle277);
		dbRManager.insert(riddle278);
		dbRManager.insert(riddle279);
		dbRManager.insert(riddle280);
		dbRManager.insert(riddle281);
		dbRManager.insert(riddle282);
		dbRManager.insert(riddle283);
		dbRManager.insert(riddle284);
		dbRManager.insert(riddle285);
		dbRManager.insert(riddle286);
		dbRManager.insert(riddle287);
		dbRManager.insert(riddle288);
		dbRManager.insert(riddle289);
		dbRManager.insert(riddle290);
		dbRManager.insert(riddle291);
		dbRManager.insert(riddle292);
		dbRManager.insert(riddle293);
		dbRManager.insert(riddle294);
		dbRManager.insert(riddle295);
		dbRManager.insert(riddle296);
		dbRManager.insert(riddle297);
		dbRManager.insert(riddle298);
		dbRManager.insert(riddle299);
		dbRManager.insert(riddle300);
		dbRManager.insert(riddle301);
		dbRManager.insert(riddle302);
		dbRManager.insert(riddle303);
		dbRManager.insert(riddle304);
		dbRManager.insert(riddle305);
		dbRManager.insert(riddle306);
		dbRManager.insert(riddle307);
		dbRManager.insert(riddle308);
		dbRManager.insert(riddle309);
		dbRManager.insert(riddle310);
		dbRManager.insert(riddle311);
		dbRManager.insert(riddle312);
		dbRManager.insert(riddle313);
		dbRManager.insert(riddle314);
		dbRManager.insert(riddle315);
		dbRManager.insert(riddle316);
		dbRManager.insert(riddle317);
		dbRManager.insert(riddle318);
		dbRManager.insert(riddle319);
		dbRManager.insert(riddle320);
		dbRManager.insert(riddle321);
		dbRManager.insert(riddle322);
		dbRManager.insert(riddle323);
		dbRManager.insert(riddle324);
		dbRManager.insert(riddle325);
		dbRManager.insert(riddle326);
		dbRManager.insert(riddle327);
		dbRManager.insert(riddle328);
		dbRManager.insert(riddle329);
		dbRManager.insert(riddle330);
		dbRManager.insert(riddle331);
		dbRManager.insert(riddle332);
		dbRManager.insert(riddle333);
		dbRManager.insert(riddle334);
		dbRManager.insert(riddle335);
		dbRManager.insert(riddle336);
		dbRManager.insert(riddle337);
		dbRManager.insert(riddle338);
		dbRManager.insert(riddle339);
		dbRManager.insert(riddle340);
		dbRManager.insert(riddle341);
		dbRManager.insert(riddle342);
		dbRManager.insert(riddle343);
		dbRManager.insert(riddle344);
		dbRManager.insert(riddle345);
		dbRManager.insert(riddle346);
		dbRManager.insert(riddle347);
		dbRManager.insert(riddle348);
		dbRManager.insert(riddle349);
		dbRManager.insert(riddle350);
		dbRManager.insert(riddle351);
		dbRManager.insert(riddle352);
		dbRManager.insert(riddle353);
		dbRManager.insert(riddle354);
		dbRManager.insert(riddle355);
		dbRManager.insert(riddle356);
		dbRManager.insert(riddle357);
		dbRManager.insert(riddle358);
		dbRManager.insert(riddle359);
		dbRManager.insert(riddle360);
		dbRManager.insert(riddle361);
		dbRManager.insert(riddle362);
		dbRManager.insert(riddle363);
		dbRManager.insert(riddle364);
		dbRManager.insert(riddle365);
		dbRManager.insert(riddle366);
		dbRManager.insert(riddle367);
		dbRManager.insert(riddle368);
		dbRManager.insert(riddle369);
		dbRManager.insert(riddle370);
		dbRManager.insert(riddle371);
		dbRManager.insert(riddle372);
		dbRManager.insert(riddle373);
		dbRManager.insert(riddle374);
		dbRManager.insert(riddle375);
		dbRManager.insert(riddle376);
		dbRManager.insert(riddle377);
		dbRManager.insert(riddle378);
		dbRManager.insert(riddle379);
		dbRManager.insert(riddle380);
		dbRManager.insert(riddle381);
		dbRManager.insert(riddle382);
		dbRManager.insert(riddle383);
		dbRManager.insert(riddle384);
		dbRManager.insert(riddle385);
		dbRManager.insert(riddle386);
		dbRManager.insert(riddle387);
		dbRManager.insert(riddle388);
		dbRManager.insert(riddle389);
		dbRManager.insert(riddle390);
		dbRManager.insert(riddle391);
		dbRManager.insert(riddle392);
		dbRManager.insert(riddle393);
		dbRManager.insert(riddle394);
		dbRManager.insert(riddle395);
		dbRManager.insert(riddle396);
		dbRManager.insert(riddle397);
		dbRManager.insert(riddle398);
		dbRManager.insert(riddle399);
		dbRManager.insert(riddle400);
		dbRManager.insert(riddle401);
		dbRManager.insert(riddle402);
		dbRManager.insert(riddle403);
		dbRManager.insert(riddle404);
		dbRManager.insert(riddle405);
		dbRManager.insert(riddle406);
		dbRManager.insert(riddle407);
		dbRManager.insert(riddle408);
		dbRManager.insert(riddle409);
		dbRManager.insert(riddle410);
		dbRManager.insert(riddle411);
		dbRManager.insert(riddle412);
		dbRManager.insert(riddle413);
		dbRManager.insert(riddle414);
		dbRManager.insert(riddle415);
		dbRManager.insert(riddle416);
		dbRManager.insert(riddle417);
		dbRManager.insert(riddle418);
		dbRManager.insert(riddle419);
		dbRManager.insert(riddle420);
		dbRManager.insert(riddle421);
		dbRManager.insert(riddle422);
		dbRManager.insert(riddle423);
		dbRManager.insert(riddle424);
		dbRManager.insert(riddle425);
		dbRManager.insert(riddle426);
		dbRManager.insert(riddle427);
		dbRManager.insert(riddle428);
		dbRManager.insert(riddle429);
		dbRManager.insert(riddle430);
		dbRManager.insert(riddle431);
		dbRManager.insert(riddle432);
		dbRManager.insert(riddle433);
		dbRManager.insert(riddle434);
		dbRManager.insert(riddle435);
		dbRManager.insert(riddle436);
		dbRManager.insert(riddle437);
		dbRManager.insert(riddle438);
		dbRManager.insert(riddle439);
		dbRManager.insert(riddle440);
		dbRManager.insert(riddle441);
		dbRManager.insert(riddle442);
		dbRManager.insert(riddle443);
		dbRManager.insert(riddle444);
		dbRManager.insert(riddle445);
		dbRManager.insert(riddle446);
		dbRManager.insert(riddle447);
		dbRManager.insert(riddle448);
		dbRManager.insert(riddle449);
		dbRManager.insert(riddle450);
		dbRManager.insert(riddle451);
		dbRManager.insert(riddle452);
		dbRManager.insert(riddle453);
		dbRManager.insert(riddle454);
		dbRManager.insert(riddle455);
		dbRManager.insert(riddle456);
		dbRManager.insert(riddle457);
		dbRManager.insert(riddle458);
		dbRManager.insert(riddle459);
		dbRManager.insert(riddle460);
		dbRManager.insert(riddle461);
		dbRManager.insert(riddle462);
		dbRManager.insert(riddle463);
		dbRManager.insert(riddle464);
		dbRManager.insert(riddle465);
		dbRManager.insert(riddle466);
		dbRManager.insert(riddle467);
		dbRManager.insert(riddle468);
		dbRManager.insert(riddle469);
		dbRManager.insert(riddle470);
		dbRManager.insert(riddle471);
		dbRManager.insert(riddle472);
		dbRManager.insert(riddle473);
		dbRManager.insert(riddle474);
		dbRManager.insert(riddle475);
		dbRManager.insert(riddle476);
		dbRManager.insert(riddle477);
		dbRManager.insert(riddle478);
		dbRManager.insert(riddle479);
		dbRManager.insert(riddle480);
		dbRManager.insert(riddle481);
		dbRManager.insert(riddle482);
		dbRManager.insert(riddle483);
		dbRManager.insert(riddle484);
		dbRManager.insert(riddle485);
		dbRManager.insert(riddle486);
		dbRManager.insert(riddle487);
		dbRManager.insert(riddle488);
		dbRManager.insert(riddle489);
		dbRManager.insert(riddle490);
		dbRManager.insert(riddle491);
		dbRManager.insert(riddle492);
		dbRManager.insert(riddle493);
		dbRManager.insert(riddle494);
		dbRManager.insert(riddle495);
		dbRManager.insert(riddle496);
		dbRManager.insert(riddle497);
		dbRManager.insert(riddle498);
		dbRManager.insert(riddle499);
		dbRManager.insert(riddle500);
		dbRManager.insert(riddle501);
		dbRManager.insert(riddle502);
		dbRManager.insert(riddle503);
		dbRManager.insert(riddle504);
		dbRManager.insert(riddle505);
		dbRManager.insert(riddle506);
		dbRManager.insert(riddle507);
		dbRManager.insert(riddle508);
		dbRManager.insert(riddle509);
		dbRManager.insert(riddle510);
		dbRManager.insert(riddle511);
		dbRManager.insert(riddle512);
		dbRManager.insert(riddle513);
		dbRManager.insert(riddle514);
		dbRManager.insert(riddle515);
		dbRManager.insert(riddle516);
		dbRManager.insert(riddle517);
		dbRManager.insert(riddle518);
		dbRManager.insert(riddle519);
		dbRManager.insert(riddle520);
		dbRManager.insert(riddle521);
		dbRManager.insert(riddle522);
		dbRManager.insert(riddle523);
		dbRManager.insert(riddle524);
		dbRManager.insert(riddle525);
		dbRManager.insert(riddle526);
		dbRManager.insert(riddle527);
		dbRManager.insert(riddle528);
		dbRManager.insert(riddle529);
		dbRManager.insert(riddle530);
		dbRManager.insert(riddle531);
		dbRManager.insert(riddle532);
		dbRManager.insert(riddle533);
		dbRManager.insert(riddle534);
		dbRManager.insert(riddle535);
		dbRManager.insert(riddle536);
		dbRManager.insert(riddle537);
		dbRManager.insert(riddle538);
		dbRManager.insert(riddle539);
		dbRManager.insert(riddle540);
		dbRManager.insert(riddle541);
		dbRManager.insert(riddle542);
		dbRManager.insert(riddle543);
		dbRManager.insert(riddle544);
		dbRManager.insert(riddle545);
		dbRManager.insert(riddle546);
		dbRManager.insert(riddle547);
		dbRManager.insert(riddle548);
		dbRManager.insert(riddle549);
		dbRManager.insert(riddle550);
		dbRManager.insert(riddle551);
		dbRManager.insert(riddle552);
		dbRManager.insert(riddle553);
		dbRManager.insert(riddle554);
		dbRManager.insert(riddle555);
		dbRManager.insert(riddle556);
		dbRManager.insert(riddle557);
		dbRManager.insert(riddle558);
		dbRManager.insert(riddle559);
		dbRManager.insert(riddle560);

	}

	private void initTwisterData() {
		dbTManager = new DBTwisterManager(this);
		Twister twister1 = new Twister("实行减肥时，最容易瘦的是哪一个部位？", " 钱包", "经典", "无");
		Twister twister2 = new Twister("什么东西装玻璃，爱把鼻子当马骑？", " 眼镜", "经典", "无");
		Twister twister3 = new Twister(
				"一个桥载重80公斤，为什么一个重70公斤的人可以拿两个各重10公斤的球过桥？", " 颠球走", "经典", "无");
		Twister twister4 = new Twister("什么书中毛病最多？", " 医书中", "经典", "无");
		Twister twister5 = new Twister("报纸上登的消息不一定百分之百是真的，但什么消息绝对假不了？",
				" 报纸上的年、月、日", "经典", "无");
		Twister twister6 = new Twister("什么票最值钱也最不值钱？", " 股票", "经典", "无");
		Twister twister7 = new Twister("一对健康夫妇，为什么生出只有一只右眼的婴儿？", " 每个人都只有一只右眼",
				"经典", "无");
		Twister twister8 = new Twister("先有男人，还是先有女人?", " 因为男人是先生的，所以叫“先生”",
				"经典", "无");
		Twister twister9 = new Twister("什么桥下没水？", " 立交桥", "经典", "无");
		Twister twister10 = new Twister("来电了怎么办", " 看电视呀", "经典", "无");
		Twister twister11 = new Twister("除了玻璃、瓷器等容易碎的东西要小心轻放外，还有什么东西要小心轻放？",
				" 屁", "经典", "无");
		Twister twister12 = new Twister("一个小姑娘在打排球，她发了一个球，可无论谁都接不着，为什麽？",
				" 球没发出去，或者球出界了", "经典", "无");
		Twister twister13 = new Twister("金钟奖、金马奖、金像奖哪个对国家贡献最大？", " 金钟奖(精忠报国)",
				"经典", "无");
		Twister twister14 = new Twister("什么书谁也没见过?", " 天书", "经典", "无");
		Twister twister15 = new Twister("你在一年半的时间都不会说话，这段时间你在干什么？", " 刚出生在哭",
				"经典", "无");
		Twister twister16 = new Twister("某人有喝一打高粱的酒量，但今晚他只喝了半瓶啤酒就醉了，为什么？",
				" 他不久前才刚喝了一打高梁", "经典", "无");
		Twister twister17 = new Twister("一斤棉花和一斤铁块哪一样比较重？", " 一样重", "经典", "无");
		Twister twister18 = new Twister("先有鸡还是先有蛋？", " 先有蛋，因为在新华字典里面蛋在鸡的前面",
				"经典", "无");
		Twister twister19 = new Twister("考试时应注意什么？", " 监考老师", "经典", "无");
		Twister twister20 = new Twister("亚当和夏娃结婚后，最大的遗憾是什么？", " 没有人来喝喜酒", "经典",
				"无");
		Twister twister21 = new Twister("明星出入公共场所，最怕遇到什么事？", " 没人找他签名", "经典",
				"无");
		Twister twister22 = new Twister("怎么称呼一只不会叫的狗？", " 狗", "经典", "无");
		Twister twister23 = new Twister("大龙家今晚的电视为什么只有图像，没有声音？", " 上演的是哑剧",
				"经典", "无");
		Twister twister24 = new Twister("虽然只是薄薄的一片，女人少了它一天也活不下去的是东西？？", " 镜子",
				"经典", "无");
		Twister twister25 = new Twister("小涵捉到一只小鸟，她把小鸟放在桌子上，小鸟却没有飞，是什么原因？",
				" 鸟已死", "经典", "无");
		Twister twister26 = new Twister(
				"五月五日是我国人民传统节日：端午节，是伟大诗人屈原投江的日子，那么你知道五月十二日是什么日子吗?",
				" 屈原烧“头七”的日子", "经典", "无");
		Twister twister27 = new Twister(
				"我国文化源远流长，唐代有诗，宋代有词，元代有曲，明代有小说，那么现在有什么？", " 参考书", "经典", "无");
		Twister twister28 = new Twister("有个人说用牙齿可以判断鸡的年龄，为什么？", " 牙可以嚼出肉的老嫩",
				"经典", "无");
		Twister twister29 = new Twister("除了变色龙以外，什么动物最擅长伪装术?", " 人", "经典", "无");
		Twister twister30 = new Twister("四年级三班是怎样上珠算的？", " 各打各的算盘", "经典", "无");
		Twister twister31 = new Twister(
				"戴维一家五口外出旅游，说好一人带一瓶饮料可戴维坚持只带4瓶可口可乐，为什么？", " 还有一瓶是汽水", "经典", "无");
		Twister twister32 = new Twister("什么样的人物照片你看不出照的是谁?", " X光片", "经典", "无");
		Twister twister33 = new Twister("第一个登上月球的中国姑娘是谁？", " 嫦娥", "经典", "无");
		Twister twister34 = new Twister("老王从九岁开始有虫牙，为什么90岁时他的牙都还在？", " 虫早已换掉",
				"经典", "无");
		Twister twister35 = new Twister("人身上的什么东西不怕冷?", " 鼻涕", "经典", "无");
		Twister twister36 = new Twister(
				"有一样东西，它每个月都会来一次，只是薄薄的一张纸就令你心烦意乱，这到底是什么东西呢？", " 每个月的成绩单", "经典",
				"无");
		Twister twister37 = new Twister("小华说他能在1秒钟之内把房间和房间里的玩具都变没了，这可能吗?",
				" 把眼睛闭上", "经典", "无");
		Twister twister38 = new Twister("世界上人口最多是哪个国家？", " 联合国", "经典", "无");
		Twister twister39 = new Twister("阿兵哥们为什么会把手榴弹叫做铁蛋？", " 碰上了铁定完蛋", "经典",
				"无");
		Twister twister40 = new Twister("向日葵会随着太阳而转动，请问阴天的时候会向着哪里？", " 向着光头的人",
				"经典", "无");
		Twister twister41 = new Twister("两只蚂蚁抬根杠，一只蚂蚁杠上望", " 六", "经典", "无");
		Twister twister42 = new Twister("唐老鸭最怕什么事？", " 发现原来自己是白天鹅", "经典", "无");
		Twister twister43 = new Twister("只有头却没有身体的牛，叫做什么牛？", " 一头牛", "经典", "无");
		Twister twister44 = new Twister("小偷从现场逃走，为什么没有留下脚印？", " 倒着走的", "经典",
				"无");
		Twister twister45 = new Twister("渔夫最怕什么？", " 没人吃鱼", "经典", "无");
		Twister twister46 = new Twister("一个人在什么情况下，才处于真正的任人宰割的地步？", " 手术台上",
				"经典", "无");
		Twister twister47 = new Twister(
				"公共汽车上，两个人正在热烈的交谈，可围观的人却一句话也听不到，这是因为什么？", " 这是一对聋哑人", "经典", "无");
		Twister twister48 = new Twister("為什么男人和女人会分手？", " 因为男女有「别」", "经典", "无");
		Twister twister49 = new Twister("最不能在光天化日下见人的是什么东西？", " 胶卷", "经典", "无");
		Twister twister50 = new Twister("一双鞋卖16元，一只鞋卖多少钱？", " 不卖", "经典", "无");
		Twister twister51 = new Twister("世界上最舒服的地方在哪里?", " 妈妈的肚子里", "经典", "无");
		Twister twister52 = new Twister(
				"大勇说他和学校的老师很熟，在学校里哪里都能进去，但小涵偏说他有一处地方永远也不能进去，是什么地方呢？", " 女厕所",
				"经典", "无");
		Twister twister53 = new Twister("什么表以一天慢24小时？", " 停表", "经典", "无");
		Twister twister54 = new Twister("电脑与人脑有什么不同？", " 电脑可以搬家，而人脑不行.", "经典",
				"无");
		Twister twister55 = new Twister("永远也写不好的字是什么字？", " 坏字", "经典", "无");
		Twister twister56 = new Twister(
				"纸上写着某一份命令。但是，看懂此文字的人，却绝对不能宣读命令。那么，纸上写的是什么呢？", " 级上写着，不要念出此文",
				"经典", "无");
		Twister twister57 = new Twister("阿美在事业并没有什么成就，为什么也有女强人的外号？",
				" 因为她常常强人所难", "经典", "无");
		Twister twister58 = new Twister(
				"有两个人同时来到了河边，都想过河，但却只有一条小船，而且小船只能载1个人，请问，他们能否都过河？",
				" 能，因为他们分别在河的两边", "经典", "无");
		Twister twister59 = new Twister(
				"一位服装模特儿小姐，即使在平日也穿着未经发表的新款服饰，但她常常看到穿着和她完全相同服饰的人。这是为什么？",
				" 因为她看到镜子中的自己", "经典", "无");
		Twister twister60 = new Twister("一个人午后在太阳下走，却看不见自己的影子。为什么？",
				" 阴雨天遮住了太阳", "经典", "无");
		Twister twister61 = new Twister("有一个人舔冰棒，为什么越舔冰棒越大支", " 因为他在南极舔", "经典",
				"无");
		Twister twister62 = new Twister("小呆骑在大牛身上，为什么大牛不吃草？", " 大牛是人", "经典",
				"无");
		Twister twister63 = new Twister(
				"有个男人站在时速240公里的列车顶上，虽然他不是一个会飞墙走壁的超人，但是他仍然显得从容自如、毫不紧张，为什么？",
				" 因为当时火车还没开动", "经典", "无");
		Twister twister64 = new Twister("单兵甲偷用了单兵乙的牙刷，单兵乙有B型肝炎，为什么单兵甲却没有被传染？",
				" 他拿去刷皮鞋了", "经典", "无");
		Twister twister65 = new Twister("教堂向祖父办告解之前，都要先做那件事？", " 犯罪", "经典", "无");
		Twister twister66 = new Twister(
				"某人买了易开罐农药，打算自杀，他的情人也要求同归与尽，但农药份量必须一整罐才会毙命，结果两个人都死了，为什么？",
				" 打开农药罐已坏再拿一罐", "经典", "无");
		Twister twister67 = new Twister("拿枪上侧所(地名)？", " 保定", "经典", "无");
		Twister twister68 = new Twister("冰到底是什么东西？", " 一块一块的水", "经典", "无");
		Twister twister69 = new Twister(
				"有一棵树，在距树7米的地方有一堆草，一头牛用一根3米的绳子栓着，这头牛把这堆草全吃光了，为什么?(不考虑牛的体长)",
				" 牛没有拴在树上", "经典", "无");
		Twister twister70 = new Twister(
				"小明从外面买了好多东西回来，为什么她一进社区办公室就把手中的一捆布往地上一扔?", " 拖布", "经典", "无");
		Twister twister71 = new Twister("环球旅行。猜一现代香港名星名", " 周星驰", "经典", "无");
		Twister twister72 = new Twister("保洁阿姨是什么人？", " 女人", "经典", "无");
		Twister twister73 = new Twister("给你一本杂志和一个火柴盒，你能使杂志只有三分之一放在桌边而不掉落下来吗?",
				" 把杂志掀开三分之一放在桌边，自然就不会掉下来了", "经典", "无");
		Twister twister74 = new Twister("最坚固的锁怕什么?", " 钥匙", "经典", "无");
		Twister twister75 = new Twister("小陈半夜吃泡面，为什么一面吃，一面眼盯着表？",
				" 那包面的食用期到今天", "经典", "无");
		Twister twister76 = new Twister("上课偷看“脑筋急转弯”，如果被没收了，谁最高兴？", " 老师的孩子",
				"经典", "无");
		Twister twister77 = new Twister("法国人的笑声跟我们有什么不同？", " 他们是用法语笑的", "经典",
				"无");
		Twister twister78 = new Twister("什么票最危险?", " 绑票", "经典", "无");
		Twister twister79 = new Twister("2木不成林！(打一字)", " 相", "经典", "无");
		Twister twister80 = new Twister("想想看，如果外星人来到地球，他说的第一句话将会是什么？", " 外星话",
				"经典", "无");
		Twister twister81 = new Twister("为什么大象只有一只右耳朵？", " 每只大象都有一只右耳朵", "动物",
				"无");
		Twister twister82 = new Twister("猴子会说话，为什么？", " 是他的外号码", "动物", "无");
		Twister twister83 = new Twister("一个人在回家的路上碰到10只狮子，请问他会变成什么？",
				" 十堆狮子大便", "动物", "无");
		Twister twister84 = new Twister("猪为什么没完没了地吃？", " 它想成为一只肉猪", "动物", "无");
		Twister twister85 = new Twister("猴子每分钟能掰一个玉米，在果园里，一只猴子5分钟能掰几个玉米?",
				" 没掰到一个", "动物", "无");
		Twister twister86 = new Twister("猪皮是用来作什么的呢？", " 包猪肉用的", "动物", "无");
		Twister twister87 = new Twister("强尼每天晚上做梦都梦到猫要吃他，他又不是老鼠，为什么？",
				" 他白天在迪尼斯扮演米老鼠", "动物", "无");
		Twister twister88 = new Twister("什么“猴”不能吃？", " “火侯”", "动物", "无");
		Twister twister89 = new Twister("如何防止被狗咬?", " 不要跑在狗的前面", "动物", "无");
		Twister twister90 = new Twister("打狗看主人，打虎看什么？", " 看你有没有种", "动物", "无");
		Twister twister91 = new Twister(
				"新兵阿国：“报告班长！我的豆浆里有一只苍蝇。”为什么班长却若无其事的告诉他放心喝，没有问题的？",
				" 因为馒头里的蜘蛛会把苍蝇吃掉", "动物", "无");
		Twister twister92 = new Twister("什么样的房子不能住人？？", " 蜂房", "动物", "无");
		Twister twister93 = new Twister("比细菌还小的东西是什么？", " 细菌的儿子", "动物", "无");
		Twister twister94 = new Twister("有一群小鸡在菜地里乱窜，小鸡是谁的？", " 鸡妈妈的", "动物",
				"无");
		Twister twister95 = new Twister("又一只狼经过，还是没有吃羊，再猜一海产品？", " 虾（瞎）", "动物",
				"无");
		Twister twister96 = new Twister(
				"一只蚊子顺时钟绕着一个新买的而且是没有任何质量问题的高效捕蚊灯打转，但一直不会被吸进去，为什么呢?",
				" 因为捕蚊灯没有通电", "动物", "无");
		Twister twister97 = new Twister("什么两个脑袋、六条腿、一条尾巴？", " 人骑马", "动物", "无");
		Twister twister98 = new Twister("有甲也有盔，有眼没有眉，无脚会赶路，有翅不会飞，这是什么？", " 鱼",
				"动物", "无");
		Twister twister99 = new Twister("谁是百兽之王？", " 动物园的园长", "动物", "无");
		Twister twister100 = new Twister(
				"甲、乙两只狼狗举行1000米赛跑，假如两只狼狗同时跑到终点，那么哪只狼狗出汗多？", " 狼狗不出汗", "动物", "无");
		Twister twister101 = new Twister("恐龙为什么会灭亡？", " 那个时候没有动物保护协会", "动物",
				"无");
		Twister twister102 = new Twister("丽丽和小狗一起玩，突然，她看到小狗越来越小了。是什么原因呢？",
				" 小狗离开小丽跑了，越跑越远", "动物", "无");
		Twister twister103 = new Twister("养过电子鸡和电子狗的人，会有什么感受？", " 鸡犬不宁", "动物",
				"无");
		Twister twister104 = new Twister("世界上的猪都死光了。（打一歌名）", " 至少还有你", "动物",
				"无");
		Twister twister105 = new Twister("把大象装进冰箱要几步？",
				" 三步，先打开冰箱，再把大象放进去，最后把冰箱门关上。", "动物", "无");
		Twister twister106 = new Twister("两个女人与一千只鸭子所说的话有何相似性呢？", " 无稽(鸡)之谈",
				"动物", "无");
		Twister twister107 = new Twister("龙的儿子与狗的儿子有什么差别？", " 一为龙子一为犬", "动物",
				"无");
		Twister twister108 = new Twister("为什么白鹭莺总是缩者一只脚睡觉？", " 缩两只脚就会跌", "动物",
				"无");
		Twister twister109 = new Twister("兔子为什么不吃窝边草？",
				" 如果兔子吃了窝边草的话，那它的窝就没有可以隐藏的东西了", "动物", "无");
		Twister twister110 = new Twister("为什么天上会有星星？",
				" 证明爱因斯坦的相对论天上有星星地下也有猩猩", "动物", "无");
		Twister twister111 = new Twister(
				"一棵树绑着一只虎，绳长10公尺，距树公尺处有一堆草，要如何它才能吃到那堆草？", " 老虎根本不吃草", "动物", "无");
		Twister twister112 = new Twister("为什么母鸡的腿短？", " 腿长了，生下的蛋会被子摔破", "动物",
				"无");
		Twister twister113 = new Twister("动物园的大象死了，为什么大象管理员哭得那么伤心？",
				" 他想到要挖那么大一个坑，所以就……", "动物", "无");
		Twister twister114 = new Twister("猴子在树上摘菠萝，一分钟一个，十分钟摘多少个？", " 菠萝不长在树上",
				"动物", "无");
		Twister twister115 = new Twister("说它是牛不是牛，力小能背房子走。", " 蜗牛", "动物", "无");
		Twister twister116 = new Twister("什么鱼不能吃？", " 木鱼", "动物", "无");
		Twister twister117 = new Twister("一头牛加一捆草等于什么？", " 还是一头牛", "动物", "无");
		Twister twister118 = new Twister("有一个婴儿喝了牛奶之后，一星期重了十公斤，为什么？", " 那是一头牛",
				"动物", "无");
		Twister twister119 = new Twister("有种动物，大小像只猫，长相又像虎，这是什么动物?", " 小老虎",
				"动物", "无");
		Twister twister120 = new Twister("只有头却没有身体的牛，叫做什么牛？", " 一头牛", "动物", "无");
		Twister twister121 = new Twister("一个公鸡在尖尖的房子上下了一个蛋，它会往哪边掉呢?",
				" 公鸡是不会生蛋的", "动物", "无");
		Twister twister122 = new Twister("哪儿的海不产鱼?", " 辞海", "动物", "无");
		Twister twister123 = new Twister("鱼儿为什么不说话？", " 因为他在水里", "动物", "无");
		Twister twister124 = new Twister("渔夫最怕什么？", " 没人吃鱼", "动物", "无");
		Twister twister125 = new Twister("为什么一个人一天吃9头牛？", " 吃的是蜗牛", "动物", "无");
		Twister twister126 = new Twister(
				"阿强和阿燕裸体死在一间密室中、現场只留下一滩水和一些碎玻璃，请推测他们的死因？", " 阿强和阿燕是金鱼", "动物",
				"无");
		Twister twister127 = new Twister("为什么蝙蝠会经常倒吊着？", " 因为它胃下垂", "动物", "无");
		Twister twister128 = new Twister("我常带着我的狗去晨跑，累得我和它都满头大汗，为什么？",
				" 你见过狗满头大汗吗", "动物", "无");
		Twister twister129 = new Twister("山岗上有三只狐狸，猎人开枪打死了一只，问山岗上还有几只狐狸？",
				" 一只", "动物", "无");
		Twister twister130 = new Twister(
				"电线杆上三只麻雀正在打架，当中一只不小心掉下來，为什么其他两只也跟着掉下來？", " 因为那两只拍手的时候掉了下来",
				"动物", "无");
		Twister twister131 = new Twister("为什么熊冬眠时会睡这么久？", " 没有人敢叫它起床", "动物",
				"无");
		Twister twister132 = new Twister("米的爸爸是谁？", " 米的爸爸是蝶，蝶恋花", "动物", "无");
		Twister twister133 = new Twister("为什么小白看见100元和肉骨头他选肉骨头？", " 小白是狗呀",
				"动物", "无");
		Twister twister134 = new Twister("一头公牛加一头母牛，猜三个字？", " 两头牛", "动物", "无");
		Twister twister135 = new Twister("那一种蝙蝠不用休息？", " 不修边幅（不休蝙蝠）", "动物", "无");
		Twister twister136 = new Twister(
				"饲养员将一串香蕉挂在竹竿上，要求大猩猩不搭凳子，不砍断竹枝拿下它。聪明的大猩猩想了想很快取到了香蕉。它是怎样拿到的？",
				" 把竹竿放倒", "动物", "无");
		Twister twister137 = new Twister("马的头朝南，马的尾朝哪？", " 朝下", "动物", "无");
		Twister twister138 = new Twister("一个老鼠洞里有五只老鼠，猫进洞吃了一只老鼠，洞里还剩下几只老鼠？",
				" 没了", "动物", "无");
		Twister twister139 = new Twister("大灰狼拖走了羊妈妈，小羊为什么也不声不响地跟了去？",
				" 小羊在羊妈妈的肚子里", "动物", "无");
		Twister twister140 = new Twister("5只鸡，5天生了5个蛋。100天内要100个蛋，需要多少只鸡？？",
				" 依然是五只鸡.", "动物", "无");
		Twister twister141 = new Twister("小明在图画课是交了一张全部涂黑的图画为什么老师还是算及格？",
				" 因为小明画的是一个黑人在半夜里抓乌鸦", "动物", "无");
		Twister twister142 = new Twister("乌龟为什么会突然「一个头两个大」？", " 因为乌龟正在想这个问题",
				"动物", "无");
		Twister twister143 = new Twister("一只蚂蚁从几百万米高的山峰落下来会怎么死？", " 饿死", "动物",
				"无");
		Twister twister144 = new Twister("为什么买一头牛只要一万元，而买三头牛却要五万元？",
				" 三头牛当然比一头牛贵", "动物", "无");
		Twister twister145 = new Twister("一只毛毛虫，过一条没桥的河，它怎么过去?", " 变成蝴蝶飞过去的",
				"动物", "无");
		Twister twister146 = new Twister("谁的脚常年走路不穿鞋?", " 动物的脚", "动物", "无");
		Twister twister147 = new Twister("一只已经饥饿以久的狼看见一只绵羊，却马上跑了，为什么？",
				" 因为他跑去追羊", "动物", "无");
		Twister twister148 = new Twister("为什么彤彤与壮壮第一次见面就一口咬定壮壮是喝羊奶长大的?",
				" 壮壮是一只羊", "动物", "无");
		Twister twister149 = new Twister("“水蛇”“蟒蛇”“青竹蛇”哪一个比较长？",
				" “青竹蛇”三个字比较长", "动物", "无");
		Twister twister150 = new Twister("苹果树上有一个苹果，一只小猴，请问树上有几个苹果，几只猴？",
				" 一个苹果一只猴子也没有，因为去打架去了", "动物", "无");
		Twister twister151 = new Twister("一只蜜蜂停在日历上，打一成语？", " 风和日丽", "动物", "无");
		Twister twister152 = new Twister("狐狸精最擅长迷惑男人，那么什么'精'男女一起迷?", " 酒精",
				"动物", "无");
		Twister twister153 = new Twister("黑鸡厉害还是白鸡厉害?为什么?", " 黑鸡会生白蛋，白鸡不会生黑蛋",
				"动物", "无");
		Twister twister154 = new Twister("蚊子咬在什么地方你不会觉得痒？", " 别人身上", "动物", "无");
		Twister twister155 = new Twister("何种动物最接近于人类?", " 寄生在人身体上的寄生虫", "动物",
				"无");
		Twister twister156 = new Twister("树上有100只鸟，用什么方法才能把它们全部抓住？", " 用照相机",
				"动物", "无");
		Twister twister157 = new Twister("龟兔又赛跑了，这次兔子没有偷懒、贪玩但是这次兔子还是输了，为什么?",
				" 乌龟把终点设在了海里", "动物", "无");
		Twister twister158 = new Twister("怀孕的母狗怕人踢它，可是有个家伙踢它，它既不躲避也不生气，为什么？",
				" 因为那家伙在他的肚子里踢", "动物", "无");
		Twister twister159 = new Twister("世界上哪儿的大象最小?", " 书上的", "动物", "无");
		Twister twister160 = new Twister("小涵捉到一只小鸟，她把小鸟放在桌子上，小鸟却没有飞，是什么原因？",
				" 鸟已死", "动物", "无");
		Twister twister161 = new Twister("为什么现代人越来越言而无信?", " 因为打电话比写信方便",
				"冷笑话", "无");
		Twister twister162 = new Twister(
				"根据调查，发现本国各地在同一时间，竟然有许多人说着相同英语的奇怪现象，这究竟是怎么一回事呢",
				" 原来他们都均同时收听英语广播讲座节目中的发音练习", "冷笑话", "无");
		Twister twister163 = new Twister(
				"有一天，有一班学生正在小考，有一个学生，他答出来之后，为什么老师还打了他一顿？", " 因为他把答案念给同学听",
				"冷笑话", "无");
		Twister twister164 = new Twister("什么事是林先生和林太太每天睡觉时都要做的事？", " 闭上眼睛",
				"冷笑话", "无");
		Twister twister165 = new Twister("爱斯基摩人用什么吃饭？", " 用嘴吃饭", "冷笑话", "无");
		Twister twister166 = new Twister("甲乙两位仇人以喝毒酒决定生死，为什么乙选了没毒的酒却死了？",
				" 被甲打死了", "冷笑话", "无");
		Twister twister167 = new Twister("债权和债务的最大区别是什么?", " 一个容易记住，一个最不容易记住",
				"冷笑话", "无");
		Twister twister168 = new Twister("哪种比赛，赢的得不到奖品，输的却有奖品?", " 划拳喝酒",
				"冷笑话", "无");
		Twister twister169 = new Twister("金钟奖、金马奖、金像奖哪个对国家贡献最大？", " 金钟奖(精忠报国)",
				"冷笑话", "无");
		Twister twister170 = new Twister("为什么买一头牛只要一万元，而买三头牛却要五万元？",
				" 三头牛当然比一头牛贵", "冷笑话", "无");
		Twister twister171 = new Twister("不小心溺水时，若附近沒有其他人该如何自救？",
				" 把水喝光，但是还是会胀死", "冷笑话", "无");
		Twister twister172 = new Twister(
				"晶晶洗澡时，每次都有从洗澡水里拿东西往嘴里塞的习惯，而且奇怪的是，她还不断称赞好吃。请问她在吃什么？", " 澡和枣同音",
				"冷笑话", "无");
		Twister twister173 = new Twister("麦克杰克逊为什么要去做漂白手术？", " 怕遭到不白之冤", "冷笑话",
				"无");
		Twister twister174 = new Twister("做什么事情最开心？", " 开心手术", "冷笑话", "无");
		Twister twister175 = new Twister("先有鸡还是先有蛋？", " 先有蛋，因为在新华字典里面蛋在鸡的前面",
				"冷笑话", "无");
		Twister twister176 = new Twister("华盛顿小时候砍倒他父亲的樱桃树时，他父亲为什么不马上处罚他？",
				" 因为他手头上还有斧头", "冷笑话", "无");
		Twister twister177 = new Twister("明明是个“错”字，为什么小华却偏偏说要“对”？", " 的确是个错字呀",
				"冷笑话", "无");
		Twister twister178 = new Twister("好与坏的中间是什么？", " 与", "冷笑话", "无");
		Twister twister179 = new Twister(
				"小王开医院，生意一直不是很如意，一天他的医院突然车水马龙排了一大堆人，为什么？", " 因为他在医院门口贴了今日住院3折",
				"冷笑话", "无");
		Twister twister180 = new Twister("什么数字让女士又爱又恨？", " 三八", "冷笑话", "无");
		Twister twister181 = new Twister("我和你爸爸的弟弟的儿子的同学的哥哥是什么关系?", " 没关系",
				"冷笑话", "无");
		Twister twister182 = new Twister("女人生小孩", " 血口喷人", "冷笑话", "无");
		Twister twister183 = new Twister("如果诸葛亮活着，世界现在会有什么不同？", " 会多一个人",
				"冷笑话", "无");
		Twister twister184 = new Twister(
				"一群惧内的大丈夫们正聚集在一起商量怎样重振男子汉的雄风，突然听说他们的老婆来了，大家四处逃窜，惟独一人没有跑，为什么？",
				" 吓死了", "冷笑话", "无");
		Twister twister185 = new Twister(
				"波霸参加500公尺赛跑，一路领先的波霸不慎摔了一大跤却还能得第一名，为什么？", " 因为其它人都被陷到坑里了",
				"冷笑话", "无");
		Twister twister186 = new Twister("爱斯基摩人用什么吃饭？", " 用嘴吃饭", "冷笑话", "无");
		Twister twister187 = new Twister("一百个男人无法抬起的物体，却有一女子可单手举起，此物体究竟为何?",
				" 一颗鸡蛋（仅仅一个鸡蛋，100个人没法抬）", "冷笑话", "无");
		Twister twister188 = new Twister("外国人问路，小明拼命有英语对他说，他却一点也听不懂，这是为什么？",
				" 因为他是法国人", "冷笑话", "无");
		Twister twister189 = new Twister(
				"小王是一名优秀士兵，一天他在站岗值勤时，明明看到有敌人悄悄向他摸过来，为什么他却睁一只眼闭一只眼？", " 他正在瞄准",
				"冷笑话", "无");
		Twister twister190 = new Twister("亚当和夏娃结婚后，最大的遗憾是什么？", " 没有人来喝喜酒",
				"冷笑话", "无");
		Twister twister191 = new Twister(
				"老师要学生写关于牛奶的文章，要求写200字，为什么沙米尔只写了20个字？", " 沙米写的是浓缩牛奶", "冷笑话",
				"无");
		Twister twister192 = new Twister("超人和蝙蝠侠的最大区别是什么？",
				" 蝙蝠侠把内裤穿在里面，超人把内裤穿在外面", "冷笑话", "无");
		Twister twister193 = new Twister("两位帅哥因何为了一位长相如恐龙般的女子大打出手？", " 打输的要娶她",
				"冷笑话", "无");
		Twister twister194 = new Twister("卖火柴的小女孩如果投胎转世來到现代，最有可能变成什么？",
				" 兼售打火机的槟榔西施", "冷笑话", "无");
		Twister twister195 = new Twister("爸爸答应汉森，只要考试及格，就奖励10元钱，可为什么汉森还是不及格？",
				" 为了给爸爸省钱", "冷笑话", "无");
		Twister twister196 = new Twister("东东养的鸽子在明明家下了一个蛋，请问这个蛋应属于谁的？", " 鸽子的",
				"冷笑话", "无");
		Twister twister197 = new Twister("为什么养长颈鹿最不花钱？",
				" 因为他们的脖子长，一点点食物都要走很长的路才能到肚子.", "冷笑话", "无");
		Twister twister198 = new Twister(
				"一个老老人头顶上只剩三根头发，有一天他要参加重要盛会，为什么他仍忍痛拔掉其中一根头发呢？", " 因为他要梳中分",
				"冷笑话", "无");
		Twister twister199 = new Twister("蚊子咬在什么地方你不会觉得痒？", " 别人身上", "冷笑话", "无");
		Twister twister200 = new Twister(
				"小王开医院，生意一直不是很如意，一天他的医院突然车水马龙排了一大堆人，为什么？", " 因为他在医院门口贴了今日住院3折",
				"冷笑话", "无");
		Twister twister201 = new Twister(
				"歹徒抢劫MTV店，朝店主开了一枪，店主情急之下抽出一卷影带挡，居然平安无事，为什么？", " 歹徒拿得是水枪",
				"冷笑话", "无");
		Twister twister202 = new Twister("小王住的是楼房，为什么每次出门还要上楼?", " 他住地下室",
				"冷笑话", "无");
		Twister twister203 = new Twister(
				"一只饿猫从一只胖老鼠身旁走过，为什么那只饥饿的老猫竟无动于衷继续走它的路，连看都没看这只老鼠。",
				" 这是一只可怜的瞎猫子碰到一只死耗子。", "冷笑话", "无");
		Twister twister204 = new Twister("医生手术为何带口罩?", " 怕人认出来", "冷笑话", "无");
		Twister twister205 = new Twister("为什么会有人见死不救？", " 死都死了还救什么", "冷笑话", "无");
		Twister twister206 = new Twister("一个胖子和一个瘦子一起跳楼，谁先到达地面？", " 围观的群众",
				"冷笑话", "无");
		Twister twister207 = new Twister("你每天做作业时先干什么？", " 打开本子", "冷笑话", "无");
		Twister twister208 = new Twister("你能做，我能做，大家都做；一个人能做，两个人不能一起做。这是做什么？",
				" 做梦", "冷笑话", "无");
		Twister twister209 = new Twister("什么时候是老二最爽的时候？", " 当老大不在不能欺负老二时",
				"冷笑话", "无");
		Twister twister210 = new Twister(
				"姑妈送给小花一只小猫，这只小猫没有死掉，也没有跑掉，小花也没有把它送人，为什么三个月后姑妈来小花家在没有看见小猫？",
				" 它已长成大猫了", "冷笑话", "无");
		Twister twister211 = new Twister("动物园里，大象的鼻子最长，鼻子第二长的是什么？", " 小象",
				"冷笑话", "无");
		Twister twister212 = new Twister("阿丁做起事来总是拖泥带水，为什么却从没被长官处罚过？",
				" 阿丁是盖碉堡的士兵", "冷笑话", "无");
		Twister twister213 = new Twister(
				"某岛上有只乌龟，正中央是棵椰子树，岛的旁边还有一座岛，乌龟想过去，但又不太会游水，请问它该怎么过？", " 他还在想",
				"冷笑话", "无");
		Twister twister214 = new Twister("怎么使麻雀安静下来？", " 压它一下（鸦雀无声)", "冷笑话",
				"无");
		Twister twister215 = new Twister("草本（打一化合物）", " 苯", "冷笑话", "无");
		Twister twister216 = new Twister("把什么打破了不会受到处分而会得到奖励?", " 记录", "冷笑话",
				"无");
		Twister twister217 = new Twister("阿三死了，为什么大毛理直气壮的说：“凶手不是我，绝对另有其人！”",
				" 因为阿三是电视推理剧场中的", "冷笑话", "无");
		Twister twister218 = new Twister("老鹰的绝症是什么?", " 恐高症", "冷笑话", "无");
		Twister twister219 = new Twister("大多数人是用左手端碗，右手吃饭，对吧？", " 那要嘴巴干什么",
				"冷笑话", "无");
		Twister twister220 = new Twister("嫦娥为什么后悔上广寒宫？", " 因为月亮上没有赛月饼", "冷笑话",
				"无");
		Twister twister221 = new Twister("为什么自由女神像老站在纽约港？", " 因为她坐不下去", "冷笑话",
				"无");
		Twister twister222 = new Twister("请问你，甚么狗不会叫？", " 热狗", "冷笑话", "无");
		Twister twister223 = new Twister("不小心溺水时，若附近沒有其他人该如何自救？",
				" 把水喝光，但是还是会胀死", "冷笑话", "无");
		Twister twister224 = new Twister("“只”字加一笔，变成什么字？", " 冲（先把“只”立起来）",
				"冷笑话", "无");
		Twister twister225 = new Twister("鸭蛋一打有多少个？", " 全没有了碎了", "冷笑话", "无");
		Twister twister226 = new Twister("小明点了一份全熟的牛排，但是为什么一切下去居然流出血来？",
				" 因为不小心切到手了", "冷笑话", "无");
		Twister twister227 = new Twister("亮亮的生日在三月三十日，请问是哪年的三月三十日?",
				" 每年的三月三十日", "冷笑话", "无");
		Twister twister228 = new Twister(
				"林老生大手术后换了一个人工心脏。病好了后，她的女友却马上提出分手，为什么会这样?", " 没有真心爱她", "冷笑话",
				"无");
		Twister twister229 = new Twister(
				"小明上班时间吃了红豆汤圆，经理看见后生气的说，太闲了是不是，小明回答了一句什么话把经理气的差点晕倒?", " 不，是甜的",
				"冷笑话", "无");
		Twister twister230 = new Twister(
				"小朋友在游泳池游泳，游了一阵，大勇数了数，发觉少了一个，忙向老师报告，老师却说没有少，是什么原因呢？",
				" 大勇忘了数上自己", "冷笑话", "无");
		Twister twister231 = new Twister("武士刀和日本人有什么关系？", " 他们是剖腹之交", "冷笑话",
				"无");
		Twister twister232 = new Twister("先有鸡还是先有蛋？", " 先有蛋，因为在新华字典里面蛋在鸡的前面",
				"冷笑话", "无");
		Twister twister233 = new Twister("陈先生走在路上，眼前有一张千元大钞，他明明看见了，为什么不去捡？",
				" 那张千元大钞票拿在别人手里", "冷笑话", "无");
		Twister twister234 = new Twister("大家都不想得到的是什么？", " 得病", "冷笑话", "无");
		Twister twister235 = new Twister("醉鬼是什么人？", " 宣布自己没醉的人", "冷笑话", "无");
		Twister twister236 = new Twister("哪一种竹子不长在土里？", " 爆竹", "冷笑话", "无");
		Twister twister237 = new Twister("象皮、老虎皮、狮子皮，哪一个比较差？", " 象皮(橡皮擦)",
				"冷笑话", "无");
		Twister twister238 = new Twister("比细菌还小的东西是什么？", " 细菌的儿子", "冷笑话", "无");
		Twister twister239 = new Twister(
				"小明的爸爸是警察，他眼看着儿子偷了一样东西，却没有多加管教，这是怎么回事？", " 儿子在偷笑呀", "冷笑话", "无");
		Twister twister240 = new Twister("什么蛋又能走又能跳还会说话？", " 笨蛋", "冷笑话", "无");
		Twister twister241 = new Twister("离婚的主要起因是什么？", " 结婚", "搞笑", "无");
		Twister twister242 = new Twister("排着对上厕所，打一地名？", " 伦敦", "搞笑", "无");
		Twister twister243 = new Twister("有人说，女人象一本书，那么胖女人象什么书？", " 合订本", "搞笑",
				"无");
		Twister twister244 = new Twister("老王的头发已经掉光了，可为什么他还老去理发店?", " 老王是理发师",
				"搞笑", "无");
		Twister twister245 = new Twister("妈妈叫小民去拿碟子来装菜，小民拿来了，却被骂了一顿，为什么？",
				" 他拿的是光喋", "搞笑", "无");
		Twister twister246 = new Twister("自己的缺点令自己讨厌是在什么时候？", " 在别人身上看到时",
				"搞笑", "无");
		Twister twister247 = new Twister(
				"阿贵歪点子特别多。一次，他将一根小棍子放在地上，却使任何人都无法跨过去，阿贵是怎样放的？", " 将小棍子放到墙边",
				"搞笑", "无");
		Twister twister248 = new Twister("包公的脸为什么是黑的？", " 因为额头上有个月亮月亮都是晚上出来",
				"搞笑", "无");
		Twister twister249 = new Twister("孔子和孟子有什么不同？",
				" 孔子把儿子牵在身边，而孟子把儿子放在头上", "搞笑", "无");
		Twister twister250 = new Twister("青蛙为什么能跳得比树高?", " 树不会跳", "搞笑", "无");
		Twister twister251 = new Twister("到家了，我为什么不进去?", " 忘了带钥匙", "搞笑", "无");
		Twister twister252 = new Twister(
				"一个不识字的人捂住识字的人的耳朵，让他读自己老婆的来信。他为什么要这样做？", " 怕识字的人听到老婆说什么", "搞笑",
				"无");
		Twister twister253 = new Twister("从前有只鸡，鸡的左面有只猫，右面有条狗，前面有只兔子，鸡的后面是什么？",
				" 注意第一句话，从的前面有只鸡，那么鸡的后面当然是“从”了", "搞笑", "无");
		Twister twister254 = new Twister("狼来了", " 杨桃", "搞笑", "无");
		Twister twister255 = new Twister("打狗看主人，打虎看什么？", " 看你有没有种", "搞笑", "无");
		Twister twister256 = new Twister("单兵甲偷用了单兵乙的牙刷，单兵乙有B型肝炎，为什么单兵甲却没有被传染？",
				" 他拿去刷皮鞋了", "搞笑", "无");
		Twister twister257 = new Twister("老张二十多年一直卖假货，为什么大家却认为他是大好人？",
				" 老张卖的是假发", "搞笑", "无");
		Twister twister258 = new Twister("阿忠结婚好几年了，却没生下一个孩子，这是为什么？",
				" 他生的是双胞胎", "搞笑", "无");
		Twister twister259 = new Twister("马要如何过河？", " 走日字步", "搞笑", "无");
		Twister twister260 = new Twister("小花站起来同饭桌一样高，两年之后，反而能在桌子下活动自如，为什么？",
				" 小花是一条狗", "搞笑", "无");
		Twister twister261 = new Twister("除了玻璃、瓷器等容易碎的东西要小心轻放外，还有什么东西要小心轻放？",
				" 屁", "搞笑", "无");
		Twister twister262 = new Twister("厕所里应该摆什么花？", " 五月花", "搞笑", "无");
		Twister twister263 = new Twister(
				"老师说我们的身体里有106块骨头，可是，小明说他身体里有107块骨头，这是为什么？",
				" 因为小明不小心吃下一块鱼骨头。", "搞笑", "无");
		Twister twister264 = new Twister("拆信。（打一城市名）？", " 开封", "搞笑", "无");
		Twister twister265 = new Twister("连续剧西游记中，谁最历害又聪明？", " 编剧", "搞笑", "无");
		Twister twister266 = new Twister("如果你生出的孩子只有一只右手你会怎么办?",
				" 废话，哪有人有两只右手的。", "搞笑", "无");
		Twister twister267 = new Twister(
				"公共汽车上，两个人正在热烈的交谈，可围观的人却一句话也听不到，这是因为什么？", " 这是一对聋哑人", "搞笑", "无");
		Twister twister268 = new Twister("下雪天，阿文开了暖气，关上门窗，为什么还感到很冷？", " 他在屋外",
				"搞笑", "无");
		Twister twister269 = new Twister("经理不会做饭，可有一道菜特别拿手，是什么？", " 炒鱿鱼", "搞笑",
				"无");
		Twister twister270 = new Twister("阿珠结婚后，为什么宁愿睡地板也不肯跟丈夫睡水床?",
				" 她有晕船的毛病", "搞笑", "无");
		Twister twister271 = new Twister("阿火在营光日的考试全部答对，为什么却没得到满分？",
				" 因为考的是是非题", "搞笑", "无");
		Twister twister272 = new Twister("歌仔戏和歌剧有什么不一样？",
				" 其实是一样的一个是传统歌仔戏，一个是西方歌仔戏", "搞笑", "无");
		Twister twister273 = new Twister("江家有三个女儿，大女儿、二女儿、三女儿。谁的身材最辣？",
				" 大女儿因为姜还是老的辣", "搞笑", "无");
		Twister twister274 = new Twister("的士生意不好，打一韩国明星?", " 车太贤", "搞笑", "无");
		Twister twister275 = new Twister("龙的儿子与狗的儿子有什么差别？", " 一为龙子一为犬", "搞笑",
				"无");
		Twister twister276 = new Twister(
				"小明上班时间吃了红豆汤圆，经理看见后生气的说，太闲了是不是，小明回答了一句什么话把经理气的差点晕倒?", " 不，是甜的",
				"搞笑", "无");
		Twister twister277 = new Twister("当今社会，个体户大都靠什么吃饭？", " 嘴", "搞笑", "无");
		Twister twister278 = new Twister("聪明人比一般人多了个什么？", " 心眼", "搞笑", "无");
		Twister twister279 = new Twister("如果苹果没落在牛顿头顶上，会落到哪里？", " 地上", "搞笑",
				"无");
		Twister twister280 = new Twister("办什么事睁一只眼闭一只眼比较好些?", " 射击的时候", "搞笑",
				"无");
		Twister twister281 = new Twister(
				"吃苹果时，咬了一口发现有一条虫子，觉得特别恶心；看到两条虫子，觉得更恶心；请问：看到几条虫子让人最恶心？",
				" 半条虫子", "搞笑", "无");
		Twister twister282 = new Twister("小刚从5000米高的飞机上跳伞，过了两个小时才落到地面，为什么？",
				" 他挂在了树上", "搞笑", "无");
		Twister twister283 = new Twister("空调从楼上掉下来会变成啥？", " 凶器废铁", "搞笑", "无");
		Twister twister284 = new Twister("你能做，我能做，大家都做；一个人能做，两个人不能一起做。这是做什么？",
				" 做梦", "搞笑", "无");
		Twister twister285 = new Twister("我常带着我的狗去晨跑，累得我和它都满头大汗，为什么？",
				" 你见过狗满头大汗吗", "搞笑", "无");
		Twister twister286 = new Twister("一朵插在牛粪上的鲜花是什么花？", " 牵牛花", "搞笑", "无");
		Twister twister287 = new Twister("地球没有水时像什么?", " 核桃", "搞笑", "无");
		Twister twister288 = new Twister("曹孟德赤壁惨败。（打二歌星）", " 孙悦、刘欢", "搞笑", "无");
		Twister twister289 = new Twister("敲凳子会发出“咚咚”声，那么凳子敲人会发出什么声？", " 惨叫声",
				"搞笑", "无");
		Twister twister290 = new Twister("蚂蚁、蜜蜂和蜈蚣，哪一种昆虫最不贪钱？", " 蜈蚣(因为无功不受禄)",
				"搞笑", "无");
		Twister twister291 = new Twister("大气的流动叫“气流”；河水的流动叫“水流”；那风的流动呢？",
				" 风流", "搞笑", "无");
		Twister twister292 = new Twister("为什么熊冬眠时会睡这么久？", " 没有人敢叫它起床", "搞笑",
				"无");
		Twister twister293 = new Twister(
				"阿强和阿燕裸体死在一间密室中、現场只留下一滩水和一些碎玻璃，请推测他们的死因？", " 阿强和阿燕是金鱼", "搞笑",
				"无");
		Twister twister294 = new Twister("避孕套---- 打一地名", " 包头", "搞笑", "无");
		Twister twister295 = new Twister("请解释擒贼先擒王。", " 丢了东西先驱找姓王的", "搞笑", "无");
		Twister twister296 = new Twister("阿研的口袋里共有10个硬币，漏掉了10个硬币，口袋里还有什么？",
				" 一个破洞", "搞笑", "无");
		Twister twister297 = new Twister("上课的时候，同学们都坐着上课，但是小李上每一节课都站着。为什么？",
				" 小李是老师", "搞笑", "无");
		Twister twister298 = new Twister("战场上，子弹最密集的地方在哪里？", " 在弹药运输车上", "搞笑",
				"无");
		Twister twister299 = new Twister("「双手万能」这句话，什么时候会凸槌？", " 踢球的时候", "搞笑",
				"无");
		Twister twister300 = new Twister("八点钟和九点钟有什么不一样？", " 差一点", "搞笑", "无");
		Twister twister301 = new Twister("既没下雨也没出太阳，某先生为什么要撑伞？", " 他是神经病",
				"搞笑", "无");
		Twister twister302 = new Twister(
				"六岁的小明总是喜欢把家里的闹钟整坏，妈妈为什么总是让不会修理钟表的爸爸代为修理?", " 妈妈让爸爸修理小明", "搞笑",
				"无");
		Twister twister303 = new Twister("为什么吃完晚餐后，全家都喜欢坐在电视机前看电视？",
				" 因为站久了脚会酸", "搞笑", "无");
		Twister twister304 = new Twister("贝多芬给了我们什么样的启示？", " 背了就会多分", "搞笑", "无");
		Twister twister305 = new Twister("动物园的大象死了，为什么大象管理员哭得那么伤心？",
				" 他想到要挖那么大一个坑，所以就……", "搞笑", "无");
		Twister twister306 = new Twister("为什么刘备三顾茅庐，诸葛亮才肯见他？", " 因为前两次没带礼",
				"搞笑", "无");
		Twister twister307 = new Twister("有一个人发高烧50度，他这时该找谁帮忙？", " 消防队", "搞笑",
				"无");
		Twister twister308 = new Twister("考试时，阿财一題都不会写，但是为什么突然眼睛一亮，开始振笔疾书？",
				" 他在写班級、座号、姓名。", "搞笑", "无");
		Twister twister309 = new Twister("小李昨天在客户面前骂总经理是笨蛋，结果小李被开除了，为什么？",
				" 因为小李泄露了公司的最高机密", "搞笑", "无");
		Twister twister310 = new Twister(
				"有只小北极熊早上醒来后一直追问熊妈妈，他是不是一只小浣熊，他妈妈回答：“你当然是北极熊”，可是他为什么还是不相信？",
				" 因为他觉得很冷", "搞笑", "无");
		Twister twister311 = new Twister(
				"老李带婴儿去喝酒，居然还让婴儿喝了三大杯啤酒，旁边的人为什么都不劝阻或责骂？", " 婴儿是那个人的外号", "搞笑",
				"无");
		Twister twister312 = new Twister(
				"三更半夜回家才发现忘记带钥匙，家里又没有其他人在，这时你最大的愿望是什么?", " 门忘锁了", "搞笑", "无");
		Twister twister313 = new Twister("男人泡澡堂-----打一军事用品。", " 炮弹", "搞笑", "无");
		Twister twister314 = new Twister("为什么军中的早餐一直是豆浆馒头，从不吃稀饭？", " 馒头比较好数",
				"搞笑", "无");
		Twister twister315 = new Twister("理发师最不喜欢的人是谁？", " 秃头的人", "搞笑", "无");
		Twister twister316 = new Twister("为什么夏天才有台风？", " 因为台风要冬眠", "搞笑", "无");
		Twister twister317 = new Twister("农夫养了10头牛，为什么只有19只角？", " 因为一只是犀牛",
				"搞笑", "无");
		Twister twister318 = new Twister("如果说儿童是国家未来的栋梁，那么儿童肚子里的蛔虫是什么？",
				" 当然栋梁的蛀虫了", "搞笑", "无");
		Twister twister319 = new Twister("传说中遇见白无常者“活”，遇见黑无常者“死”，那么同时遇见黑白无常呢？",
				" 吓得半死不活", "搞笑", "无");
		Twister twister320 = new Twister("胖姐阿英站上人体秤时，为何指针却只指着5？", " 指针已转过一圈了",
				"搞笑", "无");
		Twister twister321 = new Twister(
				"期终考试成绩下来了，平平的四门功课全是零分。老师却说比起某些同学来平平有一条是值得表扬的。老师指的是什么？",
				" 平平没有作弊", "儿童", "无");
		Twister twister322 = new Twister("英国出生过大人物吗？", " 没有，全是婴儿", "儿童", "无");
		Twister twister323 = new Twister("三个孩子吃三个饼要用3分钟，九十个孩子九十个饼要用多少时间?",
				" 也是三分钟，九十个孩子同时吃", "儿童", "无");
		Twister twister324 = new Twister("法王路易十四被砍头后他的儿子当了什么？", " 孤儿", "儿童",
				"无");
		Twister twister325 = new Twister("一个最贪玩的小孩最喜欢什么课?", " 下课", "儿童", "无");
		Twister twister326 = new Twister(
				"小朋友在游泳池游泳，游了一阵，大勇数了数，发觉少了一个，忙向老师报告，老师却说没有少，是什么原因呢？",
				" 大勇忘了数上自己", "儿童", "无");
		Twister twister327 = new Twister("小陈半夜吃泡面，为什么一面吃，一面眼盯着表？",
				" 那包面的食用期到今天", "儿童", "无");
		Twister twister328 = new Twister("北京王府井步行街上来往最多的是什么人？", " 行人", "儿童",
				"无");
		Twister twister329 = new Twister(
				"有一天，有一班学生正在小考，有一个学生，他答出来之后，为什么老师还打了他一顿？", " 因为他把答案念给同学听",
				"儿童", "无");
		Twister twister330 = new Twister("一架高空飞行的客机在航行中，小王突然打开门冲出去，为什么他没摔死?",
				" 他是冲进了厕所", "儿童", "无");
		Twister twister331 = new Twister("一对健康夫妇，为什么生出只有一只右眼的婴儿？",
				" 每个人都只有一只右眼", "儿童", "无");
		Twister twister332 = new Twister("不管长得多像的双胞胎，都会有人分得出来，这人是谁?", " 他们自己",
				"儿童", "无");
		Twister twister333 = new Twister("贝儿的妈妈从外地买回一种鱼，无论放多长时间也不会臭。为什么？",
				" 木鱼", "儿童", "无");
		Twister twister334 = new Twister(
				"小红和小丽是同学，也住在同一条街，她们总是一起上学，可是每天一出家门就一个向左走，一个向右走，这是怎么回事呢？",
				" 他们的家门是相对着的", "儿童", "无");
		Twister twister335 = new Twister(
				"戴维一家五口外出旅游，说好一人带一瓶饮料可戴维坚持只带4瓶可口可乐，为什么？", " 还有一瓶是汽水", "儿童", "无");
		Twister twister336 = new Twister("老师说蚯蚓切成两段仍能再生，小东照老师话去做，蚯蚓却死了，为什么?",
				" 小东是竖着将蚯蚓切开的", "儿童", "无");
		Twister twister337 = new Twister("哪种人希望孩子越多越好？", " 儿童用品制造商", "儿童", "无");
		Twister twister338 = new Twister("小杰最爱吹牛，但是为什么他说大家都说他讲话很实在？",
				" 那是他自己说的", "儿童", "无");
		Twister twister339 = new Twister(
				"有个刚生下的婴儿，有两个小孩和他是同年同月同日生的，而且是同一对父母生的，但他们不是双胞胎，这可能吗？", " 三胞胎",
				"儿童", "无");
		Twister twister340 = new Twister("为什么爷爷送给小明一份生日礼物，小明却一脚把礼物踢开？",
				" 礼物是足球", "儿童", "无");
		Twister twister341 = new Twister("冰变成水最快的方法是什么？", " 去掉两点水", "儿童", "无");
		Twister twister342 = new Twister("白冰冰与关公生的双胞胎叫什么名字？", " 粉红派对", "儿童",
				"无");
		Twister twister343 = new Twister("小杰在教室外捡到一只皮夹，为什么不交到老师那里？", " 是自己的",
				"儿童", "无");
		Twister twister344 = new Twister("沙沙声称自己是辨别母鸡年龄的专家，其绝招是用牙齿，为什么？",
				" 把鸡亲口吃了来辨别母鸡的老嫩", "儿童", "无");
		Twister twister345 = new Twister(
				"篮子里的7个莱果掉了4个在桌子上，还有一个不知掉到哪去了，飞飞把桌子上的莱果拾进篮子里，又吃了一个，请问篮子里还剩下几个苹果？",
				" 还有五个", "儿童", "无");
		Twister twister346 = new Twister("小明吃麻辣面，加了胡椒又加辣椒，你猜他还会加什么东西？",
				" 鼻涕和眼泪", "儿童", "无");
		Twister twister347 = new Twister("阿辉从来不念书。为何也能成为全校的模范生？", " 他是聋哑生",
				"儿童", "无");
		Twister twister348 = new Twister("亮亮的生日在三月三十日，请问是哪年的三月三十日?",
				" 每年的三月三十日", "儿童", "无");
		Twister twister349 = new Twister(
				"几个孩子在分一些糖果，分来分去不平均。如果每个人得3粒，还剩7粒；如果每个人得5粒，又少了3粒。请问一共有几个孩子？几粒糖？",
				" 5孩子，22颗糖果", "儿童", "无");
		Twister twister350 = new Twister("小明点了一份全熟的牛排，但是为什么一切下去居然流出血来？",
				" 因为不小心切到手了", "儿童", "无");
		Twister twister351 = new Twister("爸爸什么时候像个孩子？", " 在爷爷面前", "儿童", "无");
		Twister twister352 = new Twister("小明次次都拿第一，为什么爸爸还要骂他啊?", " 倒数第一", "儿童",
				"无");
		Twister twister353 = new Twister("爸爸丢了一样东西，为什么妈妈还特别高兴？", " 他丢掉了坏习惯",
				"儿童", "无");
		Twister twister354 = new Twister("萨维在电影院看电影时，为什么每次看的都是不连贯的电影？",
				" 每次都是看一会儿睡一会儿", "儿童", "无");
		Twister twister355 = new Twister("红和绿豆放在一个碟子里，小王为什么一下就把它们分开了?",
				" 红豆和绿豆一样只有一颗", "儿童", "无");
		Twister twister356 = new Twister(
				"冬天，宝宝怕冷，到了屋里也不肯脱帽。可是他见了一个人乖乖地脱下帽，那人是谁？", " 理发师", "儿童", "无");
		Twister twister357 = new Twister("小红和妈妈都在一年级，为什么？", " 妈妈是老师", "儿童", "无");
		Twister twister358 = new Twister(
				"老李带婴儿去喝酒，居然还让婴儿喝了三大杯啤酒，旁边的人为什么都不劝阻或责骂？", " 婴儿是那个人的外号", "儿童",
				"无");
		Twister twister359 = new Twister("考试时，小光全部都抄小明的，为什么小明得到一百分，小光却零分呢？",
				" 因为小光连名字都抄小明的", "儿童", "无");
		Twister twister360 = new Twister(
				"班长告诉菜鸟，当拉开手榴弹的保险之后，口中先数五秒再投掷出去，菜鸟一切都按班长指示动作，但仍被炸死了，为什么？",
				" 因为菜鸟有口吃", "儿童", "无");
		Twister twister361 = new Twister("为什么小白看见100元和肉骨头他选肉骨头？", " 小白是狗呀",
				"儿童", "无");
		Twister twister362 = new Twister("如果说儿童是国家未来的栋梁，那么儿童肚子里的蛔虫是什么？",
				" 当然栋梁的蛀虫了", "儿童", "无");
		Twister twister363 = new Twister(
				"老师用篮子拿来了五个苹果，准备分给五个小朋友，每个小朋友分一个，但是篮子里还要留一个，请问怎么分？",
				" 五个人分一个，分四次", "儿童", "无");
		Twister twister364 = new Twister("大龙家今晚的电视为什么只有图像，没有声音？", " 上演的是哑剧",
				"儿童", "无");
		Twister twister365 = new Twister(
				"小明家住在五楼，可是电梯坏了，他自己也没有走楼梯，他却上了五楼回到家里，这可能吗？", " 他妈妈背着他上楼", "儿童",
				"无");
		Twister twister366 = new Twister("妈妈明明在叫大宝，但出来的竟是小宝，为什么？", " 大宝不在",
				"儿童", "无");
		Twister twister367 = new Twister("华盛顿小时候砍倒他父亲的樱桃树时，他父亲为什么不马上处罚他？",
				" 因为他手头上还有斧头", "儿童", "无");
		Twister twister368 = new Twister("东东养的鸽子在明明家下了一个蛋，请问这个蛋应属于谁的？", " 鸽子的",
				"儿童", "无");
		Twister twister369 = new Twister("小明去参加讲笑话比赛，一路上小明一直用冰块敷嘴巴，为什么？",
				" 怕笑话到时候不新鲜", "儿童", "无");
		Twister twister370 = new Twister(
				"几个学生排队上校车。4个学生的前面有4个学生，4个学生的后面有4个学生，4个学生的中间也有4个学生。请问一共有几个学生？",
				" 8个", "儿童", "无");
		Twister twister371 = new Twister("我和你爸爸的弟弟的儿子的同学的哥哥是什么关系?", " 没关系",
				"儿童", "无");
		Twister twister372 = new Twister(
				"老师出了一道作文，题目是“假如我是个董事长”，同学都用心在写，为什么小强不动手？", " 他在等秘书替他写", "儿童",
				"无");
		Twister twister373 = new Twister(
				"阿昌认识了一个女孩子，对她一见钟情，得知她没有男朋友，为什么还是闷闷不乐？", " 女孩结婚了", "儿童", "无");
		Twister twister374 = new Twister(
				"大勇总爱吹牛，他说他能不用任何容器将一杯水带走，小朋友都不相信，但他却做到了，是怎么回事呢？",
				" 将杯子倒扣在装满水的盆子里", "儿童", "无");
		Twister twister375 = new Twister(
				"大勇向小伙伴们吹嘘说：今天上课的时候，老师提了一个问题，全班除了他没有一个答对，你猜老师问的是什么问题？",
				" 说:大勇你为什么又迟到了?", "儿童", "无");
		Twister twister376 = new Twister("参加联考时，除了准考证之外，最重要的是什么？", " 记得起床",
				"儿童", "无");
		Twister twister377 = new Twister("小红口袋里原有10个铜钱，但它们都掉了，请问小红口袋里还剩下什么?",
				" 还剩下一个洞", "儿童", "无");
		Twister twister378 = new Twister("爱吃零食的小王体重最重时有50公斤，但最轻时只有3公斤，为什么？",
				" 他刚出生的体重", "儿童", "无");
		Twister twister379 = new Twister("灰姑娘的老爸老妈可能是谁？", " 白雪公主与包公", "儿童", "无");
		Twister twister380 = new Twister("哥哥吹嘘自己有非凡的记忆力，可有一件事他常忘，那是什么事？",
				" 还弟弟的钱", "儿童", "无");
		Twister twister381 = new Twister("米奇吃下了药，但忘了把药摇匀，达不到最佳效果，他该如何补救？",
				" 不停地翻跟头", "儿童", "无");
		Twister twister382 = new Twister("小平平时嘴闭不住，为什么现在一声不吭?", " 小平睡着了", "儿童",
				"无");
		Twister twister383 = new Twister("冬冬的爸爸牙齿非常好，可是他经常去口腔医院，为什么?",
				" 因为他是牙科医生", "儿童", "无");
		Twister twister384 = new Twister(
				"阿强和阿燕裸体死在一间密室中、現场只留下一滩水和一些碎玻璃，请推测他们的死因？", " 阿强和阿燕是金鱼", "儿童",
				"无");
		Twister twister385 = new Twister(
				"考试做判断题，小花掷骰子决定答案，但题目有20题，为什么他却扔了40次？", " 他要验证一遍", "儿童", "无");
		Twister twister386 = new Twister("你爸爸和你妈妈生了个儿子，他既不是你哥哥又不是你弟弟，他是谁？",
				" 是你自己呀。", "儿童", "无");
		Twister twister387 = new Twister(
				"老师要学生写关于牛奶的文章，要求写200字，为什么沙米尔只写了20个字？", " 沙米写的是浓缩牛奶", "儿童", "无");
		Twister twister388 = new Twister("小涵捉到一只小鸟，她把小鸟放在桌子上，小鸟却没有飞，是什么原因？",
				" 鸟已死", "儿童", "无");
		Twister twister389 = new Twister("阿丁做起事来总是拖泥带水，为什么却从没被长官处罚过？",
				" 阿丁是盖碉堡的士兵", "儿童", "无");
		Twister twister390 = new Twister("阿姆斯壮登陆月球，他说的第一句话是什么？", " 美式英语", "儿童",
				"无");
		Twister twister391 = new Twister("小波比的一举一动都离不开绳子，为什么？", " 小波是个木偶",
				"儿童", "无");
		Twister twister392 = new Twister("小秦买了一辆全新的跑车，却不能开上马路，这是为什么？",
				" 他买的是玩具跑车", "儿童", "无");
		Twister twister393 = new Twister("阿研的口袋里共有10个硬币，漏掉了10个硬币，口袋里还有什么？",
				" 一个破洞", "儿童", "无");
		Twister twister394 = new Twister("何种动物最接近于人类?", " 寄生在人身体上的寄生虫", "儿童",
				"无");
		Twister twister395 = new Twister("为什么两个孩子恰恰好？", " 因为不孝有三", "儿童", "无");
		Twister twister396 = new Twister("如果你生出的孩子只有一只右手你会怎么办?",
				" 废话，哪有人有两只右手的。", "儿童", "无");
		Twister twister397 = new Twister("明明是个“错”字，为什么小华却偏偏说要“对”？", " 的确是个错字呀",
				"儿童", "无");
		Twister twister398 = new Twister("废除早自习会造成什么影响？", " 让学生和家长可以多赖床半小时",
				"儿童", "无");
		Twister twister399 = new Twister("丁丁拿着块石头向玻璃砸去，玻璃却没碎。为什么？", " 没砸到",
				"儿童", "无");
		Twister twister400 = new Twister("小孩子喜欢好什么？", " 好奇", "儿童", "无");
		Twister twister401 = new Twister("什么东西满屋走，但碰不着物件？", " 声音", "益智", "无");
		Twister twister402 = new Twister("一朵盛开在家里的花，却被关在笼子里。请问这是什么？", " 电风扇",
				"益智", "无");
		Twister twister403 = new Twister("什么東西越洗越脏？", " 水", "益智", "无");
		Twister twister404 = new Twister("什么鬼整天腾云驾雾？", " 烟鬼", "益智", "无");
		Twister twister405 = new Twister("在赛车比赛中，有辆车撞上大树，车子完全撞烂，开车者却毫发无伤，为什么？",
				" 那是遥控车比赛", "益智", "无");
		Twister twister406 = new Twister("什么东西载得动一百捆干草却托不起一粒沙子，日夜奔跑却离不开自己的卧床？",
				" 河流", "益智", "无");
		Twister twister407 = new Twister("什么事，你明明没有做，却要受罚?", " 回家做作业", "益智",
				"无");
		Twister twister408 = new Twister("放大镜不能放大的东西是什么？", " 角度", "益智", "无");
		Twister twister409 = new Twister("小李说“我前的人是小王”，小王说“我前面的人是小李”怎么回事？",
				" 很简单，他们面对面的站着", "益智", "无");
		Twister twister410 = new Twister("少了一本书，猜一成语？", " 缺一不可(book)", "益智",
				"无");
		Twister twister411 = new Twister("小偷从现场逃走，为什么没有留下脚印？", " 倒着走的", "益智",
				"无");
		Twister twister412 = new Twister("有一种牛皮最容易被戳穿，那是什么牛皮？", " 吹牛皮", "益智",
				"无");
		Twister twister413 = new Twister("不打不相识，打两字称谓", " 战友", "益智", "无");
		Twister twister414 = new Twister("谁是世界上最有恒心的画家？", " 爱化妆的女人", "益智", "无");
		Twister twister415 = new Twister("晴朗的天空，为什么没有太阳？？", " 晚上当然没有太阳", "益智",
				"无");
		Twister twister416 = new Twister("米的爸爸是谁？", " 米的爸爸是蝶，蝶恋花", "益智", "无");
		Twister twister417 = new Twister("水陆各半。（打一拉丁美洲国家名）", " 海地", "益智", "无");
		Twister twister418 = new Twister("在火车站绝不可能发生什么事？", " 既然不可能发生当然不能发生",
				"益智", "无");
		Twister twister419 = new Twister("什么球不能踢?", " 火球", "益智", "无");
		Twister twister420 = new Twister("一辆高速行驶的汽车在过一个90°的弯时，哪个轮子一定离开地面?",
				" 备用轮胎", "益智", "无");
		Twister twister421 = new Twister(
				"一座桥上面立有一牌，牌上写“不准过桥”。 但是很多人都照样不理睬，照样过去。你说为什么？",
				" 这座桥的名字叫“不准过桥”", "益智", "无");
		Twister twister422 = new Twister("什么水取之不尽，用之不竭？", " 口水", "益智", "无");
		Twister twister423 = new Twister("3个人3天用3桶水，9个人9天用几桶水？", " 9捅", "益智",
				"无");
		Twister twister424 = new Twister("地上有三只小鸟，打死一只，还剩几只？", " 一只", "益智", "无");
		Twister twister425 = new Twister("飞得最高的是什么?", " 人，他们飞到过月球", "益智", "无");
		Twister twister426 = new Twister("9999个无（打一成语）", " 万无一失", "益智", "无");
		Twister twister427 = new Twister("什么时候有人敲门，你绝不会说请进?", " 厕所", "益智", "无");
		Twister twister428 = new Twister("世界上最长的单词是什么？",
				" 微笑因为两个字母s里隔了一里（s-mile-s）", "益智", "无");
		Twister twister429 = new Twister("什么柴不能烧？", " 人才", "益智", "无");
		Twister twister430 = new Twister("小玲放学回家后，发现自己忘了带钥匙，她该怎么做才能进家门呢？",
				" 门没锁开开就好了", "益智", "无");
		Twister twister431 = new Twister("有一个人被从几千米的高空掉下来的东西砸在头上，却没有受伤，为什么？",
				" 砸下来的是雪花", "益智", "无");
		Twister twister432 = new Twister("什么东西越热越爱出来？", " 汗", "益智", "无");
		Twister twister433 = new Twister("一幢大楼失火，很多人围观，却无人报警，为什么?",
				" 失火的正是警察局大楼", "益智", "无");
		Twister twister434 = new Twister("有甲也有盔，有眼没有眉，无脚会赶路，有翅不会飞，这是什么？", " 鱼",
				"益智", "无");
		Twister twister435 = new Twister("一个人午后在太阳下走，却看不见自己的影子。为什么？",
				" 阴雨天遮住了太阳", "益智", "无");
		Twister twister436 = new Twister("八十多了一横，打一字。", " 平", "益智", "无");
		Twister twister437 = new Twister("怎样使用最简单的方法使X+I=IX等式成立？", " I+X",
				"益智", "无");
		Twister twister438 = new Twister("什么戏人人都演过？", " 游戏", "益智", "无");
		Twister twister439 = new Twister("什么人在刀刃上生活?", " 滑冰运动员", "益智", "无");
		Twister twister440 = new Twister("有一个东西，你能用左手拿，不能用右手拿，这东西是什么？",
				" 自己的右手", "益智", "无");
		Twister twister441 = new Twister("一个可以大可以小的地方是哪里？", " 厕所", "益智", "无");
		Twister twister442 = new Twister("小王走路从来脚不沾地，这是为什么?", " 因为穿着鞋子", "益智",
				"无");
		Twister twister443 = new Twister("什么人是人们说时很崇拜，但却不想见到的人？", " 上帝", "益智",
				"无");
		Twister twister444 = new Twister("在餐厅中吃完饭发现没带钱，怎么办？", " 刷卡或吃赊帐", "益智",
				"无");
		Twister twister445 = new Twister("什么东西有头无脚？", " 砖头", "益智", "无");
		Twister twister446 = new Twister("一次宴会上，一对夫妻同客人共握手48次，问这次宴会上共有几人？",
				" 26人", "益智", "无");
		Twister twister447 = new Twister(
				"桌子上有12支点燃的蜡烛，先被风吹灭了3根，不久又一阵风吹灭了2根，最后桌子上还剩几根蜡烛？",
				" 5根，因为没被吹灭的都燃完了", "益智", "无");
		Twister twister448 = new Twister("用三个3组成一个最大的数?", " 3的33次方", "益智", "无");
		Twister twister449 = new Twister("刘邦破咸阳—城市名一", " 秦皇岛", "益智", "无");
		Twister twister450 = new Twister("小凯开着车子，却始终到不了目的地，为什么？",
				" 他开的是公园里的环行车", "益智", "无");
		Twister twister451 = new Twister("什么样的情况下，一加一绝对不等于二？",
				" 一大杯水加进一斤面粉中，只会等于一块面团", "益智", "无");
		Twister twister452 = new Twister("什么样的水不能喝？", " 薪水", "益智", "无");
		Twister twister453 = new Twister("什么鸭子用两只脚走路？", " 所有的鸭子都是用两只脚走路", "益智",
				"无");
		Twister twister454 = new Twister("1+1不是2、王、11是什么？", " 丰", "益智", "无");
		Twister twister455 = new Twister("什么东西越吃越感到饿？", " 消化药", "益智", "无");
		Twister twister456 = new Twister("谁最喜欢添油加醋？？", " 厨师", "益智", "无");
		Twister twister457 = new Twister(
				"锐锐他能轻而易举地把一只倒悬的杯子装满水，而且不用任何东西挡住瓶口，他是怎样做的呢？",
				" 将杯子倒扣在装满水的盆子里。", "益智", "无");
		Twister twister458 = new Twister("小赵买一张奖票，中了一等奖，去领奖却不给?", " 没有到领奖的日期",
				"益智", "无");
		Twister twister459 = new Twister(
				"塑料袋里有六个橘子，如何均分给三个小孩，而塑料袋里仍有二个橘子？（不可以分开橘子）",
				" 当然是一个人两个桔子，只是一个连塑料袋一起给他.", "益智", "无");
		Twister twister460 = new Twister("什么人最喜欢日光浴？", " 植物人(光合作用)", "益智", "无");
		Twister twister461 = new Twister(
				"有三个小孩子正在猜拳，一个出剪刀，一个出石头，一个出布，请问三个小孩子的手指共有几根？", " 三十根", "益智",
				"无");
		Twister twister462 = new Twister("池无水，地无土(打一字) ？", " 也", "益智", "无");
		Twister twister463 = new Twister("用什么方法可以使人不喝水？", " 把水改名字", "益智", "无");
		Twister twister464 = new Twister("什么油不能点燃？", " 酱油", "益智", "无");
		Twister twister465 = new Twister("别人请你吃什么需要你自己花钱？", " 吃官司", "益智", "无");
		Twister twister466 = new Twister("世界上哪个地方下午比早上先到？", " 在字典里", "益智", "无");
		Twister twister467 = new Twister(
				"远在外地工作的浪人寄了封信回家慰问，里面还夹了张照片，但为什么他家人收到信后却迟迟不肯打开看呢?",
				" 粗心的浪人把勿折写成了勿拆", "益智", "无");
		Twister twister468 = new Twister("什么鬼大家都喜欢啊？", " 淘气鬼", "益智", "无");
		Twister twister469 = new Twister(
				"有一种水果，没吃之前是绿色的，吃下去是红色的，吐出时却是黑色的，請問是请问这是什么水果?", " 西瓜", "益智",
				"无");
		Twister twister470 = new Twister("山岗上有三只狐狸，猎人开枪打死了一只，问山岗上还有几只狐狸？",
				" 一只", "益智", "无");
		Twister twister471 = new Twister("车子应该靠右行使才对，为什么杨先生靠左行使却没事？",
				" 因为他正行驶在靠左行驶的国家", "益智", "无");
		Twister twister472 = new Twister("什么车子寸步难行？（除了坏车子）", " 风车", "益智", "无");
		Twister twister473 = new Twister("什么越冷越爱出来？", " 鼻涕", "益智", "无");
		Twister twister474 = new Twister("桥下只能限高十米，但是船上的货物已超过十米，该怎么办呢？",
				" 拿几块大石头放到船上船就会下沉一些", "益智", "无");
		Twister twister475 = new Twister("小张一直朝北走，走着走着他又没有转身可是却走到了正南方，为什么?",
				" 他越过北极点在向前走就是南方", "益智", "无");
		Twister twister476 = new Twister("什么桶永远装不满？", " 马桶", "益智", "无");
		Twister twister477 = new Twister(
				"李伯伯一共有7个儿子，这7个儿子又各有一个妹妹，那么，李伯伯一共有几个子女？", " 八个子女妹妹最小", "益智",
				"无");
		Twister twister478 = new Twister("煮一个蛋要四分钟，煮八个蛋要几分钟？", " 四分钟", "益智",
				"无");
		Twister twister479 = new Twister("两只蚂蚁抬根杠，一只蚂蚁杠上望", " 六", "益智", "无");
		Twister twister480 = new Twister("什么老鼠用两只脚走路？", " 米老鼠", "益智", "无");
		Twister twister481 = new Twister("促膝而谈，猜一个物理理论？", " 相对论", "很难", "无");
		Twister twister482 = new Twister(
				"患者张开嘴巴之后，牙医吓了一跳说：“哇！你的牙齿蛀了好大一个洞 ！哇！你的牙齿蛀了好大一个洞，一个洞！”请问他为什么要说两遍呢？",
				" 因为那是回音", "很难", "无");
		Twister twister483 = new Twister("什么果不能吃？", " 后果", "很难", "无");
		Twister twister484 = new Twister("4+4+4+4（猜一种水果）", " 16，石榴", "很难", "无");
		Twister twister485 = new Twister(
				"小张开车，不小心撞上电线杆发生车祸，警察到达时车上有个死人，小张说这与他无关，警察也相信了，为什么？", " 小张开灵车",
				"很难", "无");
		Twister twister486 = new Twister("有个地方发生了火灾，虽然有很多人在救火，但就是没人报火警，为什么?",
				" 消防队着火了", "很难", "无");
		Twister twister487 = new Twister("什么酒不能喝?", " 碘酒", "很难", "无");
		Twister twister488 = new Twister("哪一件衣服最耐穿？", " 最不喜欢的那件", "很难", "无");
		Twister twister489 = new Twister("妈妈叫小民去拿碟子来装菜，小民拿来了，却被骂了一顿，为什么？",
				" 他拿的是光喋", "很难", "无");
		Twister twister490 = new Twister("离婚的主要起因是什么？", " 结婚", "很难", "无");
		Twister twister491 = new Twister("四天三夜的露营活动，最令人难忘的是哪一夜？", " 宵夜", "很难",
				"无");
		Twister twister492 = new Twister("用什么方法立刻可以找到遗失的图钉？", " 光着脚走", "很难",
				"无");
		Twister twister493 = new Twister(
				"一位服装模特儿小姐，即使在平日也穿着未经发表的新款服饰，但她常常看到穿着和她完全相同服饰的人。这是为什么？",
				" 因为她看到镜子中的自己", "很难", "无");
		Twister twister494 = new Twister("中国哪个地方的东西最不便宜？", " 贵州", "很难", "无");
		Twister twister495 = new Twister("海关大钟一昼夜里时针和分钟重合多少次？", " 23", "很难",
				"无");
		Twister twister496 = new Twister("母亲节那天，你如果不想让母亲洗碗，又不想自己动手的话，你该怎么办？",
				" 跟她说：妈，留着明天洗吧", "很难", "无");
		Twister twister497 = new Twister(
				"小张被关在一间并没有上锁的房间里，可是他使出吃奶的力气也不能把门拉开，这是怎么回事？", " 推开门就行", "很难",
				"无");
		Twister twister498 = new Twister("猜猜什么东西，可以洗，不能晒，可以吃，不能吞？", " 麻将",
				"很难", "无");
		Twister twister499 = new Twister("男人在一起喝酒，为什么非划拳不可？", " 敬酒不吃吃罚酒", "很难",
				"无");
		Twister twister500 = new Twister("动物园里，小明紧挨着老虎合影留念，老虎却没有咬他，为什么？",
				" 那是只假老虎", "很难", "无");
		Twister twister501 = new Twister("一头牛加一捆草等于什么？", " 还是一头牛", "很难", "无");
		Twister twister502 = new Twister("什么样的情况下，一加一绝对不等于二？",
				" 一大杯水加进一斤面粉中，只会等于一块面团", "很难", "无");
		Twister twister503 = new Twister("小波比的一举一动都离不开绳子，为什么？", " 小波是个木偶",
				"很难", "无");
		Twister twister504 = new Twister("日月潭的中间是什么？", " 是月字", "很难", "无");
		Twister twister505 = new Twister(
				"拿手杖的瞎子阿明，走到一处未加盖的下水道洞口前，为什么没有失足掉进洞里？", " 因为忘了带手杖想起来回家拿了",
				"很难", "无");
		Twister twister506 = new Twister("一对健康的夫妇，为什么会生出没有眼睛的婴儿?", " 鸡生蛋",
				"很难", "无");
		Twister twister507 = new Twister("什么东西别人请你吃，但你自己还是要付钱？", " 吃官司", "很难",
				"无");
		Twister twister508 = new Twister("什么地方看到的月亮最大？", " 月球上", "很难", "无");
		Twister twister509 = new Twister("二十世纪最出风头的超级巨星是哪一位？",
				" 海尔波普慧星，千年才见一次", "很难", "无");
		Twister twister510 = new Twister("3个人3天用3桶水，9个人9天用几桶水？", " 9捅", "很难",
				"无");
		Twister twister511 = new Twister("为什么吃完晚餐后，全家都喜欢坐在电视机前看电视？",
				" 因为站久了脚会酸", "很难", "无");
		Twister twister512 = new Twister("释迦牟尼是男人还是女人？", " 都不是，他是神", "很难", "无");
		Twister twister513 = new Twister("为什么有人说建立在金钱基础上的婚姻是最牢固的？",
				" 铜婚，银婚，金婚……越老越牢固", "很难", "无");
		Twister twister514 = new Twister("什么布剪不断？", " 瀑布", "很难", "无");
		Twister twister515 = new Twister("小明对小华说：“我可以坐在一个你永远也坐不到的地方！”他坐在哪里？",
				" 小华的身上", "很难", "无");
		Twister twister516 = new Twister("老子为什么要骑青牛出海关？", " 老子高兴", "很难", "无");
		Twister twister517 = new Twister("小陈半夜吃泡面，为什么一面吃，一面眼盯着表？",
				" 那包面的食用期到今天", "很难", "无");
		Twister twister518 = new Twister("有什么办法可以保住母鸡性命免遭主人宰杀?", " 想办法每天下一个蛋",
				"很难", "无");
		Twister twister519 = new Twister("蛋要怎么买，才不会买到里面已经孵出了小鸡的蛋？", " 买鸭蛋",
				"很难", "无");
		Twister twister520 = new Twister(
				"有一天小董上完物理课后，突然想效法牛顿，就到苹果树下，这时也刚好掉下一颗苹果，砸到小董的头，你猜小董怎么说？",
				" 这个苹果是熟的", "很难", "无");
		Twister twister521 = new Twister(
				"小明一家人在客厅里，明明听到有人喊：“救命啊，失火了，为什么他们一家人动也不动？？？", " 因为他们在看电视",
				"很难", "无");
		Twister twister522 = new Twister("胖姐阿英站上人体秤时，为何指针却只指着5？", " 指针已转过一圈了",
				"很难", "无");
		Twister twister523 = new Twister("动物园的大象死了，为什么大象管理员哭得那么伤心？",
				" 他想到要挖那么大一个坑，所以就……", "很难", "无");
		Twister twister524 = new Twister("10除2却不等于五，为什么？", " 情报小组除去两名汉奸还有8名",
				"很难", "无");
		Twister twister525 = new Twister("老张不小心吞了一枚金币，为什么到十年后才去手术取出来呢？",
				" 因为当时不急着用钱", "很难", "无");
		Twister twister526 = new Twister(
				"在一次监察严密的考试中，有两个学生交了一模一样的考卷。主考官发现后，却并没有认为他们作弊，这是什么原因？",
				" 二张考卷交得都是白卷", "很难", "无");
		Twister twister527 = new Twister("每个成功男人背后有一个女人，那一个失败的男人背后会有什么？",
				" 有太多的女人", "很难", "无");
		Twister twister528 = new Twister(
				"两个棋友一天共下了9盘棋，在没有和局的情况下他俩赢的次数相同，怎么回事?", " 9盘不全是他们两个人一起下的",
				"很难", "无");
		Twister twister529 = new Twister("农夫养了10头牛，为什么只有19只角？", " 因为一只是犀牛",
				"很难", "无");
		Twister twister530 = new Twister("在路上，它翻了一个跟斗，接着又翻了一次（猜4字成语）?",
				" 三翻两次", "很难", "无");
		Twister twister531 = new Twister("米的妈妈是谁？", " 米的妈妈是花，花生米", "很难", "无");
		Twister twister532 = new Twister(
				"两个人住在一个胡同里，只隔几步路，他们同在一个工厂上班，但每天出门上班，却总一个向左，一个向右，为什么?",
				" 他们住对门", "很难", "无");
		Twister twister533 = new Twister(
				"有一位大师武功了得，他在下雨天不带任何防雨物品出门，全身都被淋湿了，可是头发一点没湿，怎么回事?",
				" 他是和尚，没有头发", "很难", "无");
		Twister twister534 = new Twister("这封信是两颗蛋做的（四字成语）?", " 信誓旦旦(蛋蛋)", "很难",
				"无");
		Twister twister535 = new Twister("卖水的人看到河会怎么想？", " 这些都是钱", "很难", "无");
		Twister twister536 = new Twister("地球上什么东西每天要走的距离最远?", " 地球", "很难", "无");
		Twister twister537 = new Twister(
				"老师给萨姆布置了一篇作文，题目是；什么是懒惰。萨姆用最简短的文字写下了这篇作文，他是怎么写的？", " 这就是懒惰",
				"很难", "无");
		Twister twister538 = new Twister(
				"班长告诉菜鸟，当拉开手榴弹的保险之后，口中先数五秒再投掷出去，菜鸟一切都按班长指示动作，但仍被炸死了，为什么？",
				" 因为菜鸟有口吃", "很难", "无");
		Twister twister539 = new Twister("5只鸡，5天生了5个蛋。100天内要100个蛋，需要多少只鸡？？",
				" 依然是五只鸡.", "很难", "无");
		Twister twister540 = new Twister("哪一个字永远写不好?", " “坏”字", "很难", "无");
		Twister twister541 = new Twister(
				"锐锐他能轻而易举地把一只倒悬的杯子装满水，而且不用任何东西挡住瓶口，他是怎样做的呢？",
				" 将杯子倒扣在装满水的盆子里。", "很难", "无");
		Twister twister542 = new Twister("喝工夫茶(打一成语)？", " 苦尽甘来", "很难", "无");
		Twister twister543 = new Twister("阿火在营光日的考试全部答对，为什么却没得到满分？",
				" 因为考的是是非题", "很难", "无");
		Twister twister544 = new Twister("二姑娘（打一字）", " 姿", "很难", "无");
		Twister twister545 = new Twister(
				"一群惧内的大丈夫们正聚集在一起商量怎样重振男子汉的雄风，突然听说他们的老婆来了，大家四处逃窜，惟独一人没有跑，为什么？",
				" 吓死了", "很难", "无");
		Twister twister546 = new Twister("当你对一件事忍无可忍时，你会怎么处理？", " 赶快上厕所", "很难",
				"无");
		Twister twister547 = new Twister("怀孕的母狗怕人踢它，可是有个家伙踢它，它既不躲避也不生气，为什么？",
				" 因为那家伙在他的肚子里踢", "很难", "无");
		Twister twister548 = new Twister("有一个人，他是你父母生的，但他却不是你的兄弟姐妹，他是谁？",
				" 你自己", "很难", "无");
		Twister twister549 = new Twister("某个人到外国去了，可是，周围全是中国人，这是怎么回事？", " 在中国",
				"很难", "无");
		Twister twister550 = new Twister("要形容女孩子好看，说什么话她最高兴？", " 谎话", "很难", "无");
		Twister twister551 = new Twister("什么東西越洗越脏？", " 水", "很难", "无");
		Twister twister552 = new Twister(
				"足球比赛中间休息的时候，爸爸问他的儿子：放在右脚旁边而左脚碰不到的是什么东西？儿子灵机一动就答对了，你知道吗？",
				" 是左脚", "很难", "无");
		Twister twister553 = new Twister("有一个人被从几千米的高空掉下来的东西砸在头上，却没有受伤，为什么？",
				" 砸下来的是雪花", "很难", "无");
		Twister twister554 = new Twister("世界上什么最大？", " 眼皮", "很难", "无");
		Twister twister555 = new Twister("人为什么要生两只耳朵？", " 兼听则明", "很难", "无");
		Twister twister556 = new Twister("张飞的妈妈姓什么？", " 吴，无（吴）事（氏）生非（飞）", "很难",
				"无");
		Twister twister557 = new Twister("茉莉花，太阳花，玫瑰花，哪一朵花最没力 ?",
				" 茉莉花因为好一朵没力(茉莉花)花", "很难", "无");
		Twister twister558 = new Twister(
				"一只毛毛虫（八只脚）走上一堆牛粪，下地以后却发现只有六只脚印，为 什么？", " 牛粪很臭两只脚捏住了鼻子", "很难",
				"无");
		Twister twister559 = new Twister("如果苹果没落在牛顿头顶上，会落到哪里？", " 地上", "很难",
				"无");
		Twister twister560 = new Twister("有一段100公尺长的铁轨上面每隔1公分放一条横木，请问共有几条横木？",
				" 0，因为钢轨上不放横木", "很难", "无");

		dbTManager.insert(twister1);
		dbTManager.insert(twister2);
		dbTManager.insert(twister3);
		dbTManager.insert(twister4);
		dbTManager.insert(twister5);
		dbTManager.insert(twister6);
		dbTManager.insert(twister7);
		dbTManager.insert(twister8);
		dbTManager.insert(twister9);
		dbTManager.insert(twister10);
		dbTManager.insert(twister11);
		dbTManager.insert(twister12);
		dbTManager.insert(twister13);
		dbTManager.insert(twister14);
		dbTManager.insert(twister15);
		dbTManager.insert(twister16);
		dbTManager.insert(twister17);
		dbTManager.insert(twister18);
		dbTManager.insert(twister19);
		dbTManager.insert(twister20);
		dbTManager.insert(twister21);
		dbTManager.insert(twister22);
		dbTManager.insert(twister23);
		dbTManager.insert(twister24);
		dbTManager.insert(twister25);
		dbTManager.insert(twister26);
		dbTManager.insert(twister27);
		dbTManager.insert(twister28);
		dbTManager.insert(twister29);
		dbTManager.insert(twister30);
		dbTManager.insert(twister31);
		dbTManager.insert(twister32);
		dbTManager.insert(twister33);
		dbTManager.insert(twister34);
		dbTManager.insert(twister35);
		dbTManager.insert(twister36);
		dbTManager.insert(twister37);
		dbTManager.insert(twister38);
		dbTManager.insert(twister39);
		dbTManager.insert(twister40);
		dbTManager.insert(twister41);
		dbTManager.insert(twister42);
		dbTManager.insert(twister43);
		dbTManager.insert(twister44);
		dbTManager.insert(twister45);
		dbTManager.insert(twister46);
		dbTManager.insert(twister47);
		dbTManager.insert(twister48);
		dbTManager.insert(twister49);
		dbTManager.insert(twister50);
		dbTManager.insert(twister51);
		dbTManager.insert(twister52);
		dbTManager.insert(twister53);
		dbTManager.insert(twister54);
		dbTManager.insert(twister55);
		dbTManager.insert(twister56);
		dbTManager.insert(twister57);
		dbTManager.insert(twister58);
		dbTManager.insert(twister59);
		dbTManager.insert(twister60);
		dbTManager.insert(twister61);
		dbTManager.insert(twister62);
		dbTManager.insert(twister63);
		dbTManager.insert(twister64);
		dbTManager.insert(twister65);
		dbTManager.insert(twister66);
		dbTManager.insert(twister67);
		dbTManager.insert(twister68);
		dbTManager.insert(twister69);
		dbTManager.insert(twister70);
		dbTManager.insert(twister71);
		dbTManager.insert(twister72);
		dbTManager.insert(twister73);
		dbTManager.insert(twister74);
		dbTManager.insert(twister75);
		dbTManager.insert(twister76);
		dbTManager.insert(twister77);
		dbTManager.insert(twister78);
		dbTManager.insert(twister79);
		dbTManager.insert(twister80);
		dbTManager.insert(twister81);
		dbTManager.insert(twister82);
		dbTManager.insert(twister83);
		dbTManager.insert(twister84);
		dbTManager.insert(twister85);
		dbTManager.insert(twister86);
		dbTManager.insert(twister87);
		dbTManager.insert(twister88);
		dbTManager.insert(twister89);
		dbTManager.insert(twister90);
		dbTManager.insert(twister91);
		dbTManager.insert(twister92);
		dbTManager.insert(twister93);
		dbTManager.insert(twister94);
		dbTManager.insert(twister95);
		dbTManager.insert(twister96);
		dbTManager.insert(twister97);
		dbTManager.insert(twister98);
		dbTManager.insert(twister99);
		dbTManager.insert(twister100);
		dbTManager.insert(twister101);
		dbTManager.insert(twister102);
		dbTManager.insert(twister103);
		dbTManager.insert(twister104);
		dbTManager.insert(twister105);
		dbTManager.insert(twister106);
		dbTManager.insert(twister107);
		dbTManager.insert(twister108);
		dbTManager.insert(twister109);
		dbTManager.insert(twister110);
		dbTManager.insert(twister111);
		dbTManager.insert(twister112);
		dbTManager.insert(twister113);
		dbTManager.insert(twister114);
		dbTManager.insert(twister115);
		dbTManager.insert(twister116);
		dbTManager.insert(twister117);
		dbTManager.insert(twister118);
		dbTManager.insert(twister119);
		dbTManager.insert(twister120);
		dbTManager.insert(twister121);
		dbTManager.insert(twister122);
		dbTManager.insert(twister123);
		dbTManager.insert(twister124);
		dbTManager.insert(twister125);
		dbTManager.insert(twister126);
		dbTManager.insert(twister127);
		dbTManager.insert(twister128);
		dbTManager.insert(twister129);
		dbTManager.insert(twister130);
		dbTManager.insert(twister131);
		dbTManager.insert(twister132);
		dbTManager.insert(twister133);
		dbTManager.insert(twister134);
		dbTManager.insert(twister135);
		dbTManager.insert(twister136);
		dbTManager.insert(twister137);
		dbTManager.insert(twister138);
		dbTManager.insert(twister139);
		dbTManager.insert(twister140);
		dbTManager.insert(twister141);
		dbTManager.insert(twister142);
		dbTManager.insert(twister143);
		dbTManager.insert(twister144);
		dbTManager.insert(twister145);
		dbTManager.insert(twister146);
		dbTManager.insert(twister147);
		dbTManager.insert(twister148);
		dbTManager.insert(twister149);
		dbTManager.insert(twister150);
		dbTManager.insert(twister151);
		dbTManager.insert(twister152);
		dbTManager.insert(twister153);
		dbTManager.insert(twister154);
		dbTManager.insert(twister155);
		dbTManager.insert(twister156);
		dbTManager.insert(twister157);
		dbTManager.insert(twister158);
		dbTManager.insert(twister159);
		dbTManager.insert(twister160);
		dbTManager.insert(twister161);
		dbTManager.insert(twister162);
		dbTManager.insert(twister163);
		dbTManager.insert(twister164);
		dbTManager.insert(twister165);
		dbTManager.insert(twister166);
		dbTManager.insert(twister167);
		dbTManager.insert(twister168);
		dbTManager.insert(twister169);
		dbTManager.insert(twister170);
		dbTManager.insert(twister171);
		dbTManager.insert(twister172);
		dbTManager.insert(twister173);
		dbTManager.insert(twister174);
		dbTManager.insert(twister175);
		dbTManager.insert(twister176);
		dbTManager.insert(twister177);
		dbTManager.insert(twister178);
		dbTManager.insert(twister179);
		dbTManager.insert(twister180);
		dbTManager.insert(twister181);
		dbTManager.insert(twister182);
		dbTManager.insert(twister183);
		dbTManager.insert(twister184);
		dbTManager.insert(twister185);
		dbTManager.insert(twister186);
		dbTManager.insert(twister187);
		dbTManager.insert(twister188);
		dbTManager.insert(twister189);
		dbTManager.insert(twister190);
		dbTManager.insert(twister191);
		dbTManager.insert(twister192);
		dbTManager.insert(twister193);
		dbTManager.insert(twister194);
		dbTManager.insert(twister195);
		dbTManager.insert(twister196);
		dbTManager.insert(twister197);
		dbTManager.insert(twister198);
		dbTManager.insert(twister199);
		dbTManager.insert(twister200);
		dbTManager.insert(twister201);
		dbTManager.insert(twister202);
		dbTManager.insert(twister203);
		dbTManager.insert(twister204);
		dbTManager.insert(twister205);
		dbTManager.insert(twister206);
		dbTManager.insert(twister207);
		dbTManager.insert(twister208);
		dbTManager.insert(twister209);
		dbTManager.insert(twister210);
		dbTManager.insert(twister211);
		dbTManager.insert(twister212);
		dbTManager.insert(twister213);
		dbTManager.insert(twister214);
		dbTManager.insert(twister215);
		dbTManager.insert(twister216);
		dbTManager.insert(twister217);
		dbTManager.insert(twister218);
		dbTManager.insert(twister219);
		dbTManager.insert(twister220);
		dbTManager.insert(twister221);
		dbTManager.insert(twister222);
		dbTManager.insert(twister223);
		dbTManager.insert(twister224);
		dbTManager.insert(twister225);
		dbTManager.insert(twister226);
		dbTManager.insert(twister227);
		dbTManager.insert(twister228);
		dbTManager.insert(twister229);
		dbTManager.insert(twister230);
		dbTManager.insert(twister231);
		dbTManager.insert(twister232);
		dbTManager.insert(twister233);
		dbTManager.insert(twister234);
		dbTManager.insert(twister235);
		dbTManager.insert(twister236);
		dbTManager.insert(twister237);
		dbTManager.insert(twister238);
		dbTManager.insert(twister239);
		dbTManager.insert(twister240);
		dbTManager.insert(twister241);
		dbTManager.insert(twister242);
		dbTManager.insert(twister243);
		dbTManager.insert(twister244);
		dbTManager.insert(twister245);
		dbTManager.insert(twister246);
		dbTManager.insert(twister247);
		dbTManager.insert(twister248);
		dbTManager.insert(twister249);
		dbTManager.insert(twister250);
		dbTManager.insert(twister251);
		dbTManager.insert(twister252);
		dbTManager.insert(twister253);
		dbTManager.insert(twister254);
		dbTManager.insert(twister255);
		dbTManager.insert(twister256);
		dbTManager.insert(twister257);
		dbTManager.insert(twister258);
		dbTManager.insert(twister259);
		dbTManager.insert(twister260);
		dbTManager.insert(twister261);
		dbTManager.insert(twister262);
		dbTManager.insert(twister263);
		dbTManager.insert(twister264);
		dbTManager.insert(twister265);
		dbTManager.insert(twister266);
		dbTManager.insert(twister267);
		dbTManager.insert(twister268);
		dbTManager.insert(twister269);
		dbTManager.insert(twister270);
		dbTManager.insert(twister271);
		dbTManager.insert(twister272);
		dbTManager.insert(twister273);
		dbTManager.insert(twister274);
		dbTManager.insert(twister275);
		dbTManager.insert(twister276);
		dbTManager.insert(twister277);
		dbTManager.insert(twister278);
		dbTManager.insert(twister279);
		dbTManager.insert(twister280);
		dbTManager.insert(twister281);
		dbTManager.insert(twister282);
		dbTManager.insert(twister283);
		dbTManager.insert(twister284);
		dbTManager.insert(twister285);
		dbTManager.insert(twister286);
		dbTManager.insert(twister287);
		dbTManager.insert(twister288);
		dbTManager.insert(twister289);
		dbTManager.insert(twister290);
		dbTManager.insert(twister291);
		dbTManager.insert(twister292);
		dbTManager.insert(twister293);
		dbTManager.insert(twister294);
		dbTManager.insert(twister295);
		dbTManager.insert(twister296);
		dbTManager.insert(twister297);
		dbTManager.insert(twister298);
		dbTManager.insert(twister299);
		dbTManager.insert(twister300);
		dbTManager.insert(twister301);
		dbTManager.insert(twister302);
		dbTManager.insert(twister303);
		dbTManager.insert(twister304);
		dbTManager.insert(twister305);
		dbTManager.insert(twister306);
		dbTManager.insert(twister307);
		dbTManager.insert(twister308);
		dbTManager.insert(twister309);
		dbTManager.insert(twister310);
		dbTManager.insert(twister311);
		dbTManager.insert(twister312);
		dbTManager.insert(twister313);
		dbTManager.insert(twister314);
		dbTManager.insert(twister315);
		dbTManager.insert(twister316);
		dbTManager.insert(twister317);
		dbTManager.insert(twister318);
		dbTManager.insert(twister319);
		dbTManager.insert(twister320);
		dbTManager.insert(twister321);
		dbTManager.insert(twister322);
		dbTManager.insert(twister323);
		dbTManager.insert(twister324);
		dbTManager.insert(twister325);
		dbTManager.insert(twister326);
		dbTManager.insert(twister327);
		dbTManager.insert(twister328);
		dbTManager.insert(twister329);
		dbTManager.insert(twister330);
		dbTManager.insert(twister331);
		dbTManager.insert(twister332);
		dbTManager.insert(twister333);
		dbTManager.insert(twister334);
		dbTManager.insert(twister335);
		dbTManager.insert(twister336);
		dbTManager.insert(twister337);
		dbTManager.insert(twister338);
		dbTManager.insert(twister339);
		dbTManager.insert(twister340);
		dbTManager.insert(twister341);
		dbTManager.insert(twister342);
		dbTManager.insert(twister343);
		dbTManager.insert(twister344);
		dbTManager.insert(twister345);
		dbTManager.insert(twister346);
		dbTManager.insert(twister347);
		dbTManager.insert(twister348);
		dbTManager.insert(twister349);
		dbTManager.insert(twister350);
		dbTManager.insert(twister351);
		dbTManager.insert(twister352);
		dbTManager.insert(twister353);
		dbTManager.insert(twister354);
		dbTManager.insert(twister355);
		dbTManager.insert(twister356);
		dbTManager.insert(twister357);
		dbTManager.insert(twister358);
		dbTManager.insert(twister359);
		dbTManager.insert(twister360);
		dbTManager.insert(twister361);
		dbTManager.insert(twister362);
		dbTManager.insert(twister363);
		dbTManager.insert(twister364);
		dbTManager.insert(twister365);
		dbTManager.insert(twister366);
		dbTManager.insert(twister367);
		dbTManager.insert(twister368);
		dbTManager.insert(twister369);
		dbTManager.insert(twister370);
		dbTManager.insert(twister371);
		dbTManager.insert(twister372);
		dbTManager.insert(twister373);
		dbTManager.insert(twister374);
		dbTManager.insert(twister375);
		dbTManager.insert(twister376);
		dbTManager.insert(twister377);
		dbTManager.insert(twister378);
		dbTManager.insert(twister379);
		dbTManager.insert(twister380);
		dbTManager.insert(twister381);
		dbTManager.insert(twister382);
		dbTManager.insert(twister383);
		dbTManager.insert(twister384);
		dbTManager.insert(twister385);
		dbTManager.insert(twister386);
		dbTManager.insert(twister387);
		dbTManager.insert(twister388);
		dbTManager.insert(twister389);
		dbTManager.insert(twister390);
		dbTManager.insert(twister391);
		dbTManager.insert(twister392);
		dbTManager.insert(twister393);
		dbTManager.insert(twister394);
		dbTManager.insert(twister395);
		dbTManager.insert(twister396);
		dbTManager.insert(twister397);
		dbTManager.insert(twister398);
		dbTManager.insert(twister399);
		dbTManager.insert(twister400);
		dbTManager.insert(twister401);
		dbTManager.insert(twister402);
		dbTManager.insert(twister403);
		dbTManager.insert(twister404);
		dbTManager.insert(twister405);
		dbTManager.insert(twister406);
		dbTManager.insert(twister407);
		dbTManager.insert(twister408);
		dbTManager.insert(twister409);
		dbTManager.insert(twister410);
		dbTManager.insert(twister411);
		dbTManager.insert(twister412);
		dbTManager.insert(twister413);
		dbTManager.insert(twister414);
		dbTManager.insert(twister415);
		dbTManager.insert(twister416);
		dbTManager.insert(twister417);
		dbTManager.insert(twister418);
		dbTManager.insert(twister419);
		dbTManager.insert(twister420);
		dbTManager.insert(twister421);
		dbTManager.insert(twister422);
		dbTManager.insert(twister423);
		dbTManager.insert(twister424);
		dbTManager.insert(twister425);
		dbTManager.insert(twister426);
		dbTManager.insert(twister427);
		dbTManager.insert(twister428);
		dbTManager.insert(twister429);
		dbTManager.insert(twister430);
		dbTManager.insert(twister431);
		dbTManager.insert(twister432);
		dbTManager.insert(twister433);
		dbTManager.insert(twister434);
		dbTManager.insert(twister435);
		dbTManager.insert(twister436);
		dbTManager.insert(twister437);
		dbTManager.insert(twister438);
		dbTManager.insert(twister439);
		dbTManager.insert(twister440);
		dbTManager.insert(twister441);
		dbTManager.insert(twister442);
		dbTManager.insert(twister443);
		dbTManager.insert(twister444);
		dbTManager.insert(twister445);
		dbTManager.insert(twister446);
		dbTManager.insert(twister447);
		dbTManager.insert(twister448);
		dbTManager.insert(twister449);
		dbTManager.insert(twister450);
		dbTManager.insert(twister451);
		dbTManager.insert(twister452);
		dbTManager.insert(twister453);
		dbTManager.insert(twister454);
		dbTManager.insert(twister455);
		dbTManager.insert(twister456);
		dbTManager.insert(twister457);
		dbTManager.insert(twister458);
		dbTManager.insert(twister459);
		dbTManager.insert(twister460);
		dbTManager.insert(twister461);
		dbTManager.insert(twister462);
		dbTManager.insert(twister463);
		dbTManager.insert(twister464);
		dbTManager.insert(twister465);
		dbTManager.insert(twister466);
		dbTManager.insert(twister467);
		dbTManager.insert(twister468);
		dbTManager.insert(twister469);
		dbTManager.insert(twister470);
		dbTManager.insert(twister471);
		dbTManager.insert(twister472);
		dbTManager.insert(twister473);
		dbTManager.insert(twister474);
		dbTManager.insert(twister475);
		dbTManager.insert(twister476);
		dbTManager.insert(twister477);
		dbTManager.insert(twister478);
		dbTManager.insert(twister479);
		dbTManager.insert(twister480);
		dbTManager.insert(twister481);
		dbTManager.insert(twister482);
		dbTManager.insert(twister483);
		dbTManager.insert(twister484);
		dbTManager.insert(twister485);
		dbTManager.insert(twister486);
		dbTManager.insert(twister487);
		dbTManager.insert(twister488);
		dbTManager.insert(twister489);
		dbTManager.insert(twister490);
		dbTManager.insert(twister491);
		dbTManager.insert(twister492);
		dbTManager.insert(twister493);
		dbTManager.insert(twister494);
		dbTManager.insert(twister495);
		dbTManager.insert(twister496);
		dbTManager.insert(twister497);
		dbTManager.insert(twister498);
		dbTManager.insert(twister499);
		dbTManager.insert(twister500);
		dbTManager.insert(twister501);
		dbTManager.insert(twister502);
		dbTManager.insert(twister503);
		dbTManager.insert(twister504);
		dbTManager.insert(twister505);
		dbTManager.insert(twister506);
		dbTManager.insert(twister507);
		dbTManager.insert(twister508);
		dbTManager.insert(twister509);
		dbTManager.insert(twister510);
		dbTManager.insert(twister511);
		dbTManager.insert(twister512);
		dbTManager.insert(twister513);
		dbTManager.insert(twister514);
		dbTManager.insert(twister515);
		dbTManager.insert(twister516);
		dbTManager.insert(twister517);
		dbTManager.insert(twister518);
		dbTManager.insert(twister519);
		dbTManager.insert(twister520);
		dbTManager.insert(twister521);
		dbTManager.insert(twister522);
		dbTManager.insert(twister523);
		dbTManager.insert(twister524);
		dbTManager.insert(twister525);
		dbTManager.insert(twister526);
		dbTManager.insert(twister527);
		dbTManager.insert(twister528);
		dbTManager.insert(twister529);
		dbTManager.insert(twister530);
		dbTManager.insert(twister531);
		dbTManager.insert(twister532);
		dbTManager.insert(twister533);
		dbTManager.insert(twister534);
		dbTManager.insert(twister535);
		dbTManager.insert(twister536);
		dbTManager.insert(twister537);
		dbTManager.insert(twister538);
		dbTManager.insert(twister539);
		dbTManager.insert(twister540);
		dbTManager.insert(twister541);
		dbTManager.insert(twister542);
		dbTManager.insert(twister543);
		dbTManager.insert(twister544);
		dbTManager.insert(twister545);
		dbTManager.insert(twister546);
		dbTManager.insert(twister547);
		dbTManager.insert(twister548);
		dbTManager.insert(twister549);
		dbTManager.insert(twister550);
		dbTManager.insert(twister551);
		dbTManager.insert(twister552);
		dbTManager.insert(twister553);
		dbTManager.insert(twister554);
		dbTManager.insert(twister555);
		dbTManager.insert(twister556);
		dbTManager.insert(twister557);
		dbTManager.insert(twister558);
		dbTManager.insert(twister559);
		dbTManager.insert(twister560);

	}

	private void initSayingData() {
		dbSManager = new DBSayingManager(this);

		Saying saying1 = new Saying("布袋里塞稻秆", " 草包", "经典", "无");
		Saying saying2 = new Saying("缺牙啃西瓜", " 道道多", "经典", "无");
		Saying saying3 = new Saying("黄鹤楼上看行人", " 把人看矮了", "经典", "无");
		Saying saying4 = new Saying("茅坑里捡铜板", " 臭钱", "经典", "无");
		Saying saying5 = new Saying("射击场上的靶子", " 漏洞百出", "经典", "无");
		Saying saying6 = new Saying("蜜糖罐子打醋", " 不知酸甜", "经典", "无");
		Saying saying7 = new Saying("胡子上挂霜", " 一吹就了", "经典", "无");
		Saying saying8 = new Saying("脚踏擀面杖", " 立场不稳；左右摇摆；摇摆不定；不稳当", "经典", "无");
		Saying saying9 = new Saying("玉米棵上戴凉帽", " 凑人头", "经典", "无");
		Saying saying10 = new Saying("跳河闭眼睛", " 横了心", "经典", "无");
		Saying saying11 = new Saying("作坊里的石磨", " 推一推，动一动", "经典", "无");
		Saying saying12 = new Saying("烟袋杆里插席篾儿", " 气不顺", "经典", "无");
		Saying saying13 = new Saying("鼻孔里氏瘤子", " 气不顺", "经典", "无");
		Saying saying14 = new Saying("寒暑表里的水银柱", " 能上能下", "经典", "无");
		Saying saying15 = new Saying("热恋中的情人", " 难分开", "经典", "无");
		Saying saying16 = new Saying("筛子装水", " 漏洞百出；漏洞多", "经典", "无");
		Saying saying17 = new Saying("熟透的大枣", " 自来红", "经典", "无");
		Saying saying18 = new Saying("带刺的铁丝", " 难缠", "经典", "无");
		Saying saying19 = new Saying("傻子活了九十八", " 虚度年华", "经典", "无");
		Saying saying20 = new Saying("扳不倒照镜子", " 里外不是人", "经典", "无");
		Saying saying21 = new Saying("对着聋子打鼓", " 充耳不闻", "经典", "无");
		Saying saying22 = new Saying("豆腐架子", " 碰不得；不牢", "经典", "无");
		Saying saying23 = new Saying("舞台上的皮影戏", " 幕后操纵", "经典", "无");
		Saying saying24 = new Saying("捡个孩子唱大戏", " 看你庆哪家的功", "经典", "无");
		Saying saying25 = new Saying("挖耳勺刨地", " 小抠", "经典", "无");
		Saying saying26 = new Saying("膝盖上打瞌睡", " 自己靠自己", "经典", "无");
		Saying saying27 = new Saying("见了强盗喊爸爸", " 认贼作父", "经典", "无");
		Saying saying28 = new Saying("泥瓦匠出身", " 和稀泥", "经典", "无");
		Saying saying29 = new Saying("十字路口贴告示", " 众所周知", "经典", "无");
		Saying saying30 = new Saying("口吹喇叭脚敲锣", " 能者多劳", "经典", "无");
		Saying saying31 = new Saying("眼前埋地雷", " 一触即发", "经典", "无");
		Saying saying32 = new Saying("一本经书看到老", " 墨守成规；食古不化", "经典", "无");
		Saying saying33 = new Saying("劳动号子", " 一呼百应", "经典", "无");
		Saying saying34 = new Saying("飞机上跳舞", " 空喜", "经典", "无");
		Saying saying35 = new Saying("半道上捡个喇叭", " 有吹的了", "经典", "无");
		Saying saying36 = new Saying("三个小鬼丢了俩", " 失魂落魄", "经典", "无");
		Saying saying37 = new Saying("鞭杆做大梁", " 不是正经东西", "经典", "无");
		Saying saying38 = new Saying("独木桥上遇仇人", " 冤家路窄；分外眼红", "经典", "无");
		Saying saying39 = new Saying("猛将军出征", " 不获全胜不收兵", "经典", "无");
		Saying saying40 = new Saying("老太太吃炒胡豆", " 咬牙切齿", "经典", "无");
		Saying saying41 = new Saying("米筛子当玩具", " 耍心眼", "经典", "无");
		Saying saying42 = new Saying("幺儿子娶媳妇", " 大事完毕", "经典", "无");
		Saying saying43 = new Saying("夫妻推磨", " 尽绕圈子；绕圈子", "经典", "无");
		Saying saying44 = new Saying("吃米不记种田人", " 忘本", "经典", "无");
		Saying saying45 = new Saying("大流子的弟弟", " 二流子", "经典", "无");
		Saying saying46 = new Saying("瞎子跳加官", " 盲目乐观", "经典", "无");
		Saying saying47 = new Saying("杀妻求将", " 官迷心窍", "经典", "无");
		Saying saying48 = new Saying("胭脂当粉搽", " 闹了个大红脸", "经典", "无");
		Saying saying49 = new Saying("森林里撒网", " 瞎张罗", "经典", "无");
		Saying saying50 = new Saying("拿菜刀哄孩子", " 不是闹着玩的", "经典", "无");
		Saying saying51 = new Saying("麻线穿针眼", " 过得去就行", "经典", "无");
		Saying saying52 = new Saying("嗓子里塞棉花", " 喘不上气；上气不接下气", "经典", "无");
		Saying saying53 = new Saying("跛子爬楼梯", " 步步难", "经典", "无");
		Saying saying54 = new Saying("同窑烧的砖瓦", " 一路货", "经典", "无");
		Saying saying55 = new Saying("天灵盖上长眼睛", " 目中无人", "经典", "无");
		Saying saying56 = new Saying("河心搁跳板", " 两头脱空", "经典", "无");
		Saying saying57 = new Saying("大蒜剥皮", " 层层深入", "经典", "无");
		Saying saying58 = new Saying("铁锤打到橡皮上", " 一声不响", "经典", "无");
		Saying saying59 = new Saying("醋坛里酿酒", " 坛坛酸", "经典", "无");
		Saying saying60 = new Saying("抹黑脸照镜子", " 自己吓唬自己；自找难看", "经典", "无");
		Saying saying61 = new Saying("糟鼻子不吃酒", " 枉担其名", "经典", "无");
		Saying saying62 = new Saying("地府里打官司", " 死对头", "经典", "无");
		Saying saying63 = new Saying("六个指头划拳", " 出了新招", "经典", "无");
		Saying saying64 = new Saying("天上的风筝", " 一根线在人家手里", "经典", "无");
		Saying saying65 = new Saying("鞋刷子脱毛", " 有板有眼", "经典", "无");
		Saying saying66 = new Saying("十字路口分手", " 各奔前程", "经典", "无");
		Saying saying67 = new Saying("强拉秀才成亲", " 难为圣人", "经典", "无");
		Saying saying68 = new Saying("纸补裤裆", " 越补越烂", "经典", "无");
		Saying saying69 = new Saying("染匠下河", " 大摆布；摆布", "经典", "无");
		Saying saying70 = new Saying("染坊里卖布", " 多管闲事", "经典", "无");
		Saying saying71 = new Saying("临老学绣花", " 晚了；迟了", "经典", "无");
		Saying saying72 = new Saying("罗锅子上树", " 钱", "经典", "无");
		Saying saying73 = new Saying("掉了帽子喊鞋", " 头上一句，脚下一句", "经典", "无");
		Saying saying74 = new Saying("冷天吞了热汤圆", " 身上暖烘烘，心上甜滋滋", "经典", "无");
		Saying saying75 = new Saying("醋坛里泡枣核", " 尖酸", "经典", "无");
		Saying saying76 = new Saying("穿木屐上高墙", " 胆战心惊；战战兢兢", "经典", "无");
		Saying saying77 = new Saying("逆水行舟", " 顶风顶浪", "经典", "无");
		Saying saying78 = new Saying("姊妹找婆家", " 各得其所", "经典", "无");
		Saying saying79 = new Saying("烂汽车过朽桥", " 乘人之危", "经典", "无");
		Saying saying80 = new Saying("套袖改袜子", " 没底儿", "经典", "无");
		Saying saying81 = new Saying("爬上屋脊的螃蟹", " 横行到顶了", "动物", "无");
		Saying saying82 = new Saying("咸鱼下水", " 假新鲜", "动物", "无");
		Saying saying83 = new Saying("荷花池里养鱼", " 一举两得", "动物", "无");
		Saying saying84 = new Saying("耗子爬铁丝", " 难转弯；转不过弯来；转不得身", "动物", "无");
		Saying saying85 = new Saying("蛤蟆跳到牛背上", " 自以为大", "动物", "无");
		Saying saying86 = new Saying("烟囱里爬老鼠", " 直来直去；直进直出；直出直人", "动物", "无");
		Saying saying87 = new Saying("见惯了骆驼", " 看不出牛大来", "动物", "无");
		Saying saying88 = new Saying("肋条换猪爪", " 不上算；不合算", "动物", "无");
		Saying saying89 = new Saying("耗子嫁女", " 讲吃不讲穿", "动物", "无");
		Saying saying90 = new Saying("高射炮打老鹰", " 得不偿失", "动物", "无");
		Saying saying91 = new Saying("蝎子戴礼帽", " 小毒人", "动物", "无");
		Saying saying92 = new Saying("螃蟹教子", " 不走正道", "动物", "无");
		Saying saying93 = new Saying("河边上逮螃蟹", " 有一个捉一个", "动物", "无");
		Saying saying94 = new Saying("找擀杖摸到牛犄角", " 别扭出穹儿来了", "动物", "无");
		Saying saying95 = new Saying("骆驼撒欢", " 汉个正经样", "动物", "无");
		Saying saying96 = new Saying("老太太吃牛筋", " 食而不知其味", "动物", "无");
		Saying saying97 = new Saying("老鼠给大象指路", " 越走越窄", "动物", "无");
		Saying saying98 = new Saying("俩螃蟹打架", " 纠缠不清", "动物", "无");
		Saying saying99 = new Saying("猴子拉车", " 又蹦又跳；连蹦带跳", "动物", "无");
		Saying saying100 = new Saying("蒸馍打狗", " 有去无回", "动物", "无");
		Saying saying101 = new Saying("捏死手中鸟", " 轻而易举", "动物", "无");
		Saying saying102 = new Saying("死了的啄木鸟", " 好硬的嘴；嘴硬", "动物", "无");
		Saying saying103 = new Saying("豹子进山", " 浑身是胆", "动物", "无");
		Saying saying104 = new Saying("牛棚里养鸡", " 架子不小；好大的架子", "动物", "无");
		Saying saying105 = new Saying("猪八戒吃西瓜", " 独吞", "动物", "无");
		Saying saying106 = new Saying("牛牵鼻子马抓鬃", " 抓住了关键", "动物", "无");
		Saying saying107 = new Saying("天然牛黄", " 宝贝疙瘩", "动物", "无");
		Saying saying108 = new Saying("锦鸡进铁笼", " 身不由已；不由自主", "动物", "无");
		Saying saying109 = new Saying("孙猴子上天宫", " 得意忘形", "动物", "无");
		Saying saying110 = new Saying("狐狸钻罐子", " 藏头露尾", "动物", "无");
		Saying saying111 = new Saying("正月十五的走马灯", " 反复无常", "动物", "无");
		Saying saying112 = new Saying("笼里捉鸡", " 十拿九稳", "动物", "无");
		Saying saying113 = new Saying("城隍爷扑蝴蝶", " 慌了神", "动物", "无");
		Saying saying114 = new Saying("耗子找枪", " 窝里反", "动物", "无");
		Saying saying115 = new Saying("老母猪啃砖头", " 好硬的嘴", "动物", "无");
		Saying saying116 = new Saying("锅堂里的老鼠", " 灰溜溜", "动物", "无");
		Saying saying117 = new Saying("黑泥鳅钻进金鱼缸", " 谁跟你比美；光显自己漂亮；献丑", "动物", "无");
		Saying saying118 = new Saying("捂着眼睛捉麻雀", " 瞎摸", "动物", "无");
		Saying saying119 = new Saying("老母猪啃猪圈", " 嘴巴痒了", "动物", "无");
		Saying saying120 = new Saying("叫牛坐板凳", " 办不到；没法办", "动物", "无");
		Saying saying121 = new Saying("猴子拉弓", " 不是样子", "动物", "无");
		Saying saying122 = new Saying("风箱里的老鼠", " 两头受气", "动物", "无");
		Saying saying123 = new Saying("蜗牛赴宴", " 不速之客", "动物", "无");
		Saying saying124 = new Saying("草地上捉鸭子", " 干扑", "动物", "无");
		Saying saying125 = new Saying("黄毛鸭子下水", " 不知深浅", "动物", "无");
		Saying saying126 = new Saying("柏油烫猪头", " 连根拔", "动物", "无");
		Saying saying127 = new Saying("打鱼子碰烂船", " 倾家荡产", "动物", "无");
		Saying saying128 = new Saying("请狼来做客", " 活得不耐烦", "动物", "无");
		Saying saying129 = new Saying("马食槽边点盏灯", " 照料", "动物", "无");
		Saying saying130 = new Saying("梁头上吊王八", " 四脚无靠", "动物", "无");
		Saying saying131 = new Saying("黑天捉牛", " 摸不着角", "动物", "无");
		Saying saying132 = new Saying("老鼠给猫捋胡子", " 送死；自己找死", "动物", "无");
		Saying saying133 = new Saying("脚踩牛屎", " 一塌糊涂", "动物", "无");
		Saying saying134 = new Saying("老虎拖象", " 大干一场", "动物", "无");
		Saying saying135 = new Saying("碓窝里放鸡蛋", " 求稳", "动物", "无");
		Saying saying136 = new Saying("三分钱买个臭猪蹄", " 贱货", "动物", "无");
		Saying saying137 = new Saying("秧鸡子下田", " 顾头不顾尾", "动物", "无");
		Saying saying138 = new Saying("买死鱼放生", " 荒唐；不知死活", "动物", "无");
		Saying saying139 = new Saying("赶车不带鞭子", " 光拍马屁", "动物", "无");
		Saying saying140 = new Saying("驴尾巴吊棒槌", " 累赘", "动物", "无");
		Saying saying141 = new Saying("老子偷猪儿偷牛", " 一辈更比一辈坏", "动物", "无");
		Saying saying142 = new Saying("灯草套牯牛", " 动不得", "动物", "无");
		Saying saying143 = new Saying("独木桥上跑马", " 冒险；危险", "动物", "无");
		Saying saying144 = new Saying("猴子戴金冠", " 惹祸大王", "动物", "无");
		Saying saying145 = new Saying("房檐上逮鸡", " 不好捉弄", "动物", "无");
		Saying saying146 = new Saying("马蜂蜇秃子", " 头痛；没遮没盖", "动物", "无");
		Saying saying147 = new Saying("老牛吃草", " 吞吞吐吐", "动物", "无");
		Saying saying148 = new Saying("兔子拉车", " 不懂那一套；又蹦又跳；连蹦带跳", "动物", "无");
		Saying saying149 = new Saying("跪在老虎面前喊恩人", " 善恶不分", "动物", "无");
		Saying saying150 = new Saying("狗尾巴上系鞭炮", " 追着炸", "动物", "无");
		Saying saying151 = new Saying("老虎近身", " 开口是祸", "动物", "无");
		Saying saying152 = new Saying("水牛抓跳蚤", " 有劲使不上", "动物", "无");
		Saying saying153 = new Saying("耗子钻风箱", " 两头咬气；自找罪受", "动物", "无");
		Saying saying154 = new Saying("老母猪打架", " 全凭一张嘴；全仗嘴", "动物", "无");
		Saying saying155 = new Saying("买咸鱼放生", " 尽做冤枉事", "动物", "无");
		Saying saying156 = new Saying("马脸比猪头", " 当面出丑；一个比一个丑", "动物", "无");
		Saying saying157 = new Saying("马戏团的猴子", " 任人耍；由人玩耍", "动物", "无");
		Saying saying158 = new Saying("牛奶里掺墨汁", " 黑白混淆", "动物", "无");
		Saying saying159 = new Saying("猪八戒不成仙", " 吃了嘴的亏；全坏在嘴上", "动物", "无");
		Saying saying160 = new Saying("绑着腿的青蛙", " 跳不了啦", "动物", "无");
		Saying saying161 = new Saying("恶狗咬天", " 狂妄", "谐音", "无");
		Saying saying162 = new Saying("河里拉屎", " 只有他自己知道", "谐音", "无");
		Saying saying163 = new Saying("歪嘴吹号", " 正气不足，邪气有余", "谐音", "无");
		Saying saying164 = new Saying("司令上树", " 趾高气扬", "谐音", "无");
		Saying saying165 = new Saying("蛤蟆跳到热锅上", " 欢乐一时是一时", "谐音", "无");
		Saying saying166 = new Saying("荞麦窝里扎锥子", " 奸对奸", "谐音", "无");
		Saying saying167 = new Saying("船到码头车到站", " 停滞不前", "谐音", "无");
		Saying saying168 = new Saying("吃多了盐", " 尽讲闲话", "谐音", "无");
		Saying saying169 = new Saying("三间房子不开门", " 怪物", "谐音", "无");
		Saying saying170 = new Saying("茶壶里煮馄饨", " 一肚子话", "谐音", "无");
		Saying saying171 = new Saying("老鼠跑进食盒里", " 抓住理了", "谐音", "无");
		Saying saying172 = new Saying("湿水的大鼓", " 不想", "谐音", "无");
		Saying saying173 = new Saying("西施上庵堂", " 美妙", "谐音", "无");
		Saying saying174 = new Saying("当兵的背算盘", " 找仗打", "谐音", "无");
		Saying saying175 = new Saying("十冬腊月出房门", " 动手动", "谐音", "无");
		Saying saying176 = new Saying("张飞摆屠案", " 凶神恶熬", "谐音", "无");
		Saying saying177 = new Saying("老鼠咬旗杆", " 吃不到", "谐音", "无");
		Saying saying178 = new Saying("大水冲了菩萨", " 绝妙", "谐音", "无");
		Saying saying179 = new Saying("小朋友唱歌", " 同声同", "谐音", "无");
		Saying saying180 = new Saying("笛子配唢呐", " 想得不一样", "谐音", "无");
		Saying saying181 = new Saying("大船载太阳", " 勉强度日", "谐音", "无");
		Saying saying182 = new Saying("唢呐里吹出笛子调", " 想不到一块；想", "谐音", "无");
		Saying saying183 = new Saying("捏着鼻子吃葱", " 忍气吞声", "谐音", "无");
		Saying saying184 = new Saying("下雨往屋里跑", " 淋不到", "谐音", "无");
		Saying saying185 = new Saying("三两银子放账", " 稀少", "谐音", "无");
		Saying saying186 = new Saying("自留地里拉屎", " 泄私愤", "谐音", "无");
		Saying saying187 = new Saying("三九天穿裙子", " 美丽又动人", "谐音", "无");
		Saying saying188 = new Saying("腊月三十洗长衫", " 今年不干明年干", "谐音", "无");
		Saying saying189 = new Saying("垃圾堆里的八骏图", " 废话", "谐音", "无");
		Saying saying190 = new Saying("说书的收了三弦琴", " 不谈了", "谐音", "无");
		Saying saying191 = new Saying("老虎舔糨糊", " 不够煳嘴", "谐音", "无");
		Saying saying192 = new Saying("北极的另一端", " 难极", "谐音", "无");
		Saying saying193 = new Saying("冰凌调豆腐", " 难办", "谐音", "无");
		Saying saying194 = new Saying("南天门作揖", " 高情难", "谐音", "无");
		Saying saying195 = new Saying("庄稼老不识桂圆", " 外行", "谐音", "无");
		Saying saying196 = new Saying("拿着扫帚上杏树", " 扫杏", "谐音", "无");
		Saying saying197 = new Saying("牛屁股后的苍蝇", " 一哄而散；盯上不放", "谐音", "无");
		Saying saying198 = new Saying("桅杆顶上吹唢呐", " 四方闻名", "谐音", "无");
		Saying saying199 = new Saying("茅房里的大粪蛆", " 死里求生", "谐音", "无");
		Saying saying200 = new Saying("狐狸吵架", " 一派胡言", "谐音", "无");
		Saying saying201 = new Saying("外婆得了个小儿子", " 有救了", "谐音", "无");
		Saying saying202 = new Saying("城隍出主意", " 诡计多端", "谐音", "无");
		Saying saying203 = new Saying("胡琴与琵琶合奏", " 谈到一块去了", "谐音", "无");
		Saying saying204 = new Saying("老虎拉车", " 没人敢；不听那一套", "谐音", "无");
		Saying saying205 = new Saying("腊八儿出生", " 动手动", "谐音", "无");
		Saying saying206 = new Saying("拾粪老汉起五更", " 找死；寻死", "谐音", "无");
		Saying saying207 = new Saying("盐店的老板转行", " 不管闲事", "谐音", "无");
		Saying saying208 = new Saying("黄花鱼下挂面", " 不用言", "谐音", "无");
		Saying saying209 = new Saying("同吹两把号", " 想到一块了", "谐音", "无");
		Saying saying210 = new Saying("虾子掉在大麦上", " 忙上加忙", "谐音", "无");
		Saying saying211 = new Saying("米汤泡稀饭", " 亲上加亲", "谐音", "无");
		Saying saying212 = new Saying("铁匠拉风箱", " 柔能克刚", "谐音", "无");
		Saying saying213 = new Saying("棉花铺失火", " 谈不得；无法谈", "谐音", "无");
		Saying saying214 = new Saying("眼皮上吊炊帚", " 耍嘴", "谐音", "无");
		Saying saying215 = new Saying("弹花匠上殿", " 有功之臣", "谐音", "无");
		Saying saying216 = new Saying("泥人吃饼子", " 难言", "谐音", "无");
		Saying saying217 = new Saying("大车拉煎饼", " 贪得多", "谐音", "无");
		Saying saying218 = new Saying("落雨立当院", " 轮到头上", "谐音", "无");
		Saying saying219 = new Saying("破棉袄", " 里外孬", "谐音", "无");
		Saying saying220 = new Saying("半天云里挂口袋", " 装疯", "谐音", "无");
		Saying saying221 = new Saying("打着灯笼拾粪", " 找死", "谐音", "无");
		Saying saying222 = new Saying("不拨灯不添油", " 省心", "谐音", "无");
		Saying saying223 = new Saying("刀马旦不会刀枪", " 笨蛋；徒有虚名", "谐音", "无");
		Saying saying224 = new Saying("懒婆娘上鸡窝", " 笨蛋", "谐音", "无");
		Saying saying225 = new Saying("敲锣碰到放炮的", " 想到一点子上", "谐音", "无");
		Saying saying226 = new Saying("半夜不见枪头子", " 攮", "谐音", "无");
		Saying saying227 = new Saying("烂屁股蜘蛛", " 没事", "谐音", "无");
		Saying saying228 = new Saying("虾子掉在盐堆里", " 忙中有闲", "谐音", "无");
		Saying saying229 = new Saying("吃咸菜长大的", " 尽管闲事", "谐音", "无");
		Saying saying230 = new Saying("又敲锣鼓又放炮", " 想到一块了", "谐音", "无");
		Saying saying231 = new Saying("放路纸钱", " 瘾死人", "谐音", "无");
		Saying saying232 = new Saying("五朵梅花开一朵", " 四肢无力", "谐音", "无");
		Saying saying233 = new Saying("庙台上长草", " 慌了神", "谐音", "无");
		Saying saying234 = new Saying("两个人打排球", " 推来推去；互相推脱", "谐音", "无");
		Saying saying235 = new Saying("远洋轮出海", " 外行", "谐音", "无");
		Saying saying236 = new Saying("山坡上烤火", " 就地取材", "谐音", "无");
		Saying saying237 = new Saying("茅坑里的搅屎棍", " 文不能文", "谐音", "无");
		Saying saying238 = new Saying("卖煎饼的赔本", " 贪大了", "谐音", "无");
		Saying saying239 = new Saying("梁山泊的军师", " 无用", "谐音", "无");
		Saying saying240 = new Saying("肉骨头打鼓", " 昏冬冬", "谐音", "无");
		Saying saying241 = new Saying("开春的鸟儿", " 成双成对", "季节", "无");
		Saying saying242 = new Saying("过冬的大葱", " 皮焦根枯心不死；叶烂皮干心不死", "季节", "无");
		Saying saying243 = new Saying("夏夜走棋", " 星罗棋布", "季节", "无");
		Saying saying244 = new Saying("冬天不戴帽子", " 动脑筋", "季节", "无");
		Saying saying245 = new Saying("春笋破土", " 节节高；天天向上", "季节", "无");
		Saying saying246 = new Saying("临嫁的姑娘", " 满面春风；春风满面", "季节", "无");
		Saying saying247 = new Saying("夏天的袜子", " 可有可无", "季节", "无");
		Saying saying248 = new Saying("春苗得雨", " 正逢时", "季节", "无");
		Saying saying249 = new Saying("笼里的斑鸠", " 不知春秋", "季节", "无");
		Saying saying250 = new Saying("千年铁树开了花", " 枯木逢春；得之不易；今古奇观", "季节", "无");
		Saying saying251 = new Saying("穿寒衣摇夏扇", " 不知冷热", "季节", "无");
		Saying saying252 = new Saying("一江春水向东流", " 滚滚向前；无穷无尽", "季节", "无");
		Saying saying253 = new Saying("春天的杨柳", " 分外亲", "季节", "无");
		Saying saying254 = new Saying("春汛的鱼虾", " 随大流", "季节", "无");
		Saying saying255 = new Saying("返青的秋苗", " 节节高；节节上升", "季节", "无");
		Saying saying256 = new Saying("春蚕到死", " 怀着丝", "季节", "无");
		Saying saying257 = new Saying("夏天的扇子", " 人人欢喜；个个喜爱", "季节", "无");
		Saying saying258 = new Saying("秋后的南瓜", " 皮老心不老", "季节", "无");
		Saying saying259 = new Saying("夏天的烘笼", " 没用处；挂起来了", "季节", "无");
		Saying saying260 = new Saying("洞庭湖里涨春水", " 一浪高一浪", "季节", "无");
		Saying saying261 = new Saying("李时珍看病", " 手到病除；妙手回春", "季节", "无");
		Saying saying262 = new Saying("冬天的竹笋", " 出不了头", "季节", "无");
		Saying saying263 = new Saying("高粱撒在麦地里", " 杂种；秋后见高低", "季节", "无");
		Saying saying264 = new Saying("开春的兔子", " 成群结伙", "季节", "无");
		Saying saying265 = new Saying("秋天剥黄麻", " 扯皮", "季节", "无");
		Saying saying266 = new Saying("穿冬衣摇夏扇", " 不知冷热", "季节", "无");
		Saying saying267 = new Saying("炎夏天的火炉子", " 讨人嫌", "季节", "无");
		Saying saying268 = new Saying("舅老爷请春客", " 奉陪到底", "季节", "无");
		Saying saying269 = new Saying("冬天的蚂蚁", " 不露头", "季节", "无");
		Saying saying270 = new Saying("冬天火炉夏天扇", " 个个喜爱", "季节", "无");
		Saying saying271 = new Saying("踩虎尾，踏春冰", " 冒险", "季节", "无");
		Saying saying272 = new Saying("秋后的知了", " 没几天叫头", "季节", "无");
		Saying saying273 = new Saying("冬天吃梅子", " 寒酸", "季节", "无");
		Saying saying274 = new Saying("寒冬的电扇", " 令人生畏", "季节", "无");
		Saying saying275 = new Saying("秋后的蚂蚱", " 没几天蹦头；蹦达不了几天", "季节", "无");
		Saying saying276 = new Saying("春草闹堂", " 急中生智", "季节", "无");
		Saying saying277 = new Saying("画上的春牛", " 中看不中用", "季节", "无");
		Saying saying278 = new Saying("冬天的扇子，夏天的火炉", " 没人爱", "季节", "无");
		Saying saying279 = new Saying("秋后的核桃", " 满人", "季节", "无");
		Saying saying280 = new Saying("早春的桃花", " 红不久", "季节", "无");
		Saying saying281 = new Saying("刺笆林里的斑鸠", " 不知春秋", "季节", "无");
		Saying saying282 = new Saying("秋后的蚊子", " 嗡嗡不了几天；销声匿迹", "季节", "无");
		Saying saying283 = new Saying("石头缝里常春藤", " 两受夹；两头受挤根子硬", "季节", "无");
		Saying saying284 = new Saying("秋天的柿子", " 自来红", "季节", "无");
		Saying saying285 = new Saying("冬天贩冰棒", " 不懂买卖经；不识时务", "季节", "无");
		Saying saying286 = new Saying("秋后的丝瓜", " 满肚子私；一肚子私", "季节", "无");
		Saying saying287 = new Saying("春茶尖儿", " 又鲜又嫩", "季节", "无");
		Saying saying288 = new Saying("冬天的大葱", " 皮干叶烂心不死", "季节", "无");
		Saying saying289 = new Saying("秋后的扇子", " 没人过问", "季节", "无");
		Saying saying290 = new Saying("秋后的蛤蟆", " 没几天叫头", "季节", "无");
		Saying saying291 = new Saying("药王爷的匾", " 手到病除；妙手回春", "季节", "无");
		Saying saying292 = new Saying("冬水田里种麦子", " 怪哉", "季节", "无");
		Saying saying293 = new Saying("春凳折了靠背儿", " 无靠", "季节", "无");
		Saying saying294 = new Saying("炎夏天打冷战", " 不寒而栗", "季节", "无");
		Saying saying295 = new Saying("冬天卖扇子", " 过时货", "季节", "无");
		Saying saying296 = new Saying("春天的雷，涨潮的水", " 留不住", "季节", "无");
		Saying saying297 = new Saying("夏天的萤火虫", " 若明若暗", "季节", "无");
		Saying saying298 = new Saying("夏天的火炉", " 挨不得", "季节", "无");
		Saying saying299 = new Saying("秋蝉落地", " 闷声不响；闷声闷气；哑了", "季节", "无");
		Saying saying300 = new Saying("年画上的春牛", " 离不得", "季节", "无");
		Saying saying301 = new Saying("冬天穿汗衫", " 冷暖自己知", "季节", "无");
		Saying saying302 = new Saying("新娶的媳妇", " 春风满面", "季节", "无");
		Saying saying303 = new Saying("冬天买扇子", " 备用", "季节", "无");
		Saying saying304 = new Saying("春天的草芽", " 自发", "季节", "无");
		Saying saying305 = new Saying("秋后望田头", " 找茬儿", "季节", "无");
		Saying saying306 = new Saying("冬天的旋风", " 成不了气候", "季节", "无");
		Saying saying307 = new Saying("春天的石榴花", " 心红", "季节", "无");
		Saying saying308 = new Saying("冬天泡桐树", " 光棍一条", "季节", "无");
		Saying saying309 = new Saying("冬天进豆腐房", " 好大的气", "季节", "无");
		Saying saying310 = new Saying("老柳树发新芽", " 回春", "季节", "无");
		Saying saying311 = new Saying("冬天的蟒蛇", " 有气无力", "季节", "无");
		Saying saying312 = new Saying("穿凉鞋戴棉帽", " 顾头不顾脚；不知春秋", "季节", "无");
		Saying saying313 = new Saying("穿棉衣打扇", " 不知春秋", "季节", "无");
		Saying saying314 = new Saying("冬天的暖水瓶", " 外冷内热", "季节", "无");
		Saying saying315 = new Saying("壁上的春牛", " 离不得；跟", "季节", "无");
		Saying saying316 = new Saying("冬天的炉子", " 闲不着", "季节", "无");
		Saying saying317 = new Saying("开春的柳絮", " 满天飞", "季节", "无");
		Saying saying318 = new Saying("冬天的螃蟹", " 横行不了几时", "季节", "无");
		Saying saying319 = new Saying("春天的竹笋", " 节节向上；无依", "季节", "无");
		Saying saying320 = new Saying("冬天吃葡萄", " 寒酸", "季节", "无");
		Saying saying321 = new Saying("屎壳郎嫁蝽象", " 臭味相投", "昆虫", "无");
		Saying saying322 = new Saying("蚂蚁脖子戳一刀", " 不是出血的筒子", "昆虫", "无");
		Saying saying323 = new Saying("屎壳郎推车", " 滚蛋", "昆虫", "无");
		Saying saying324 = new Saying("嗑瓜子瞌出个臭虫", " 充人来了", "昆虫", "无");
		Saying saying325 = new Saying("蜜蜂的窝", " 窟窿多", "昆虫", "无");
		Saying saying326 = new Saying("屎壳郎滚粪蛋", " 走回头路；倒退", "昆虫", "无");
		Saying saying327 = new Saying("一枪打死个苍蝇", " 得不偿失", "昆虫", "无");
		Saying saying328 = new Saying("笼子里关蚂蚁", " 来去自由", "昆虫", "无");
		Saying saying329 = new Saying("灭虱子烧皮袄", " 因小失大", "昆虫", "无");
		Saying saying330 = new Saying("扇蒲扇打蚊子", " 一举两得", "昆虫", "无");
		Saying saying331 = new Saying("缚在线上的蚂蚱", " 跑不了", "昆虫", "无");
		Saying saying332 = new Saying("蚂蚁驮秤砣", " 好大的口气", "昆虫", "无");
		Saying saying333 = new Saying("秋后的蚊子", " 嗡嗡不了几天；销声匿迹", "昆虫", "无");
		Saying saying334 = new Saying("裤子里进蚂蚁", " 坐立不安", "昆虫", "无");
		Saying saying335 = new Saying("蚂蚁回窝", " 走老路", "昆虫", "无");
		Saying saying336 = new Saying("玻璃罩里的苍蝇", " 看到光明无出路；处处碰壁", "昆虫", "无");
		Saying saying337 = new Saying("哑巴被蜈蚣咬", " 痛不可言", "昆虫", "无");
		Saying saying338 = new Saying("蚂蚁拉石磙", " 力不能及；力不从心；心有余而力不足", "昆虫", "无");
		Saying saying339 = new Saying("苍蝇找屎克螂做亲", " 臭味相投", "昆虫", "无");
		Saying saying340 = new Saying("蚊子放屁", " 小气", "昆虫", "无");
		Saying saying341 = new Saying("大象吃蚊子", " 无从下口；无法下口；难下", "昆虫", "无");
		Saying saying342 = new Saying("蜜蜂蛰人", " 逼急", "昆虫", "无");
		Saying saying343 = new Saying("无头的苍蝇", " 瞎碰；瞎撞", "昆虫", "无");
		Saying saying344 = new Saying("蚂蚁戴谷壳", " 好大的脸皮", "昆虫", "无");
		Saying saying345 = new Saying("马槽里的苍蝇", " 混饭吃", "昆虫", "无");
		Saying saying346 = new Saying("蜜蜂的屁股", " 刺儿头", "昆虫", "无");
		Saying saying347 = new Saying("蚂蚱爬在鞭梢上", " 经不起摔打", "昆虫", "无");
		Saying saying348 = new Saying("口水沾跳蚤", " 一物降一物", "昆虫", "无");
		Saying saying349 = new Saying("蚂蚱看庄稼", " 越看越光", "昆虫", "无");
		Saying saying350 = new Saying("墙上的蜘蛛网，草原上的脚印", " 蛛丝马迹", "昆虫", "无");
		Saying saying351 = new Saying("蛤蟆吃萤火虫", " 心里亮；肚里明", "昆虫", "无");
		Saying saying352 = new Saying("蚂蚱跳塘", " 不知深浅", "昆虫", "无");
		Saying saying353 = new Saying("飞蛾撵蜘蛛", " 自投网罗", "昆虫", "无");
		Saying saying354 = new Saying("放大镜照臭虫", " 原形毕露", "昆虫", "无");
		Saying saying355 = new Saying("冬天的蚂蚁", " 不露头", "昆虫", "无");
		Saying saying356 = new Saying("蜂蜜待客", " 给他点甜头", "昆虫", "无");
		Saying saying357 = new Saying("屎壳郎出国", " 臭名远扬", "昆虫", "无");
		Saying saying358 = new Saying("蜜蜂叮镜中花", " 白费功夫", "昆虫", "无");
		Saying saying359 = new Saying("烂肉喂苍蝇", " 投其所好", "昆虫", "无");
		Saying saying360 = new Saying("恨虱子烧棉袄", " 得不偿失；不值得", "昆虫", "无");
		Saying saying361 = new Saying("蚊子飞到电灯上", " 弃暗投明", "昆虫", "无");
		Saying saying362 = new Saying("苍蝇洗脸", " 假干净", "昆虫", "无");
		Saying saying363 = new Saying("蚂蚁头上砍一刀", " 没血肉", "昆虫", "无");
		Saying saying364 = new Saying("蚊子叮鸡蛋", " 无孔不入；无缝可钻", "昆虫", "无");
		Saying saying365 = new Saying("蚂蚁搬秤砣", " 白费功夫；白费劲；枉费功", "昆虫", "无");
		Saying saying366 = new Saying("屎壳郎戴面具", " 臭不要脸", "昆虫", "无");
		Saying saying367 = new Saying("惊蛰后的蜈蚣", " 越来越凶", "昆虫", "无");
		Saying saying368 = new Saying("屎壳郎爬鞭梢", " 光知腾云驾雾，不知死在跟前", "昆虫", "无");
		Saying saying369 = new Saying("掉进浆糊盆里的苍蝇", " 拔不出腿来", "昆虫", "无");
		Saying saying370 = new Saying("蚊子咬木偶", " 找错了对象", "昆虫", "无");
		Saying saying371 = new Saying("蚂蚁生疮", " 小毛病", "昆虫", "无");
		Saying saying372 = new Saying("断了翅膀的苍蝇", " 嗡嗡不了几天", "昆虫", "无");
		Saying saying373 = new Saying("屎壳郎跌粪坑", " 饱餐一顿；死里求生", "昆虫", "无");
		Saying saying374 = new Saying("大吊车吊蚂蚁", " 轻而易举", "昆虫", "无");
		Saying saying375 = new Saying("苍蝇放屁", " 吓谁哩", "昆虫", "无");
		Saying saying376 = new Saying("屎壳郎放屁", " 不值一文", "昆虫", "无");
		Saying saying377 = new Saying("屎壳郎打饱嗝儿", " 满嘴喷粪", "昆虫", "无");
		Saying saying378 = new Saying("屎壳郎戴耳环", " 摆臭阔气", "昆虫", "无");
		Saying saying379 = new Saying("蚂蚁抖腿", " 小踢蹬", "昆虫", "无");
		Saying saying380 = new Saying("蚂蚁下塘", " 不知深浅", "昆虫", "无");
		Saying saying381 = new Saying("蚊子趴在玻璃上", " 插不上嘴；难插嘴", "昆虫", "无");
		Saying saying382 = new Saying("蚊子的力气", " 大不了", "昆虫", "无");
		Saying saying383 = new Saying("蜂儿没嘴", " 屁股伤人", "昆虫", "无");
		Saying saying384 = new Saying("蚂蚁抬食", " 步调一致", "昆虫", "无");
		Saying saying385 = new Saying("掐了头的苍蝇", " 乱撞；不知死活；死活不知", "昆虫", "无");
		Saying saying386 = new Saying("春天的蜜蜂", " 闲不住", "昆虫", "无");
		Saying saying387 = new Saying("蚂蚁碰上鸡", " 活该", "昆虫", "无");
		Saying saying388 = new Saying("光头上面长虱子", " 无处藏身", "昆虫", "无");
		Saying saying389 = new Saying("嗑瓜子嗑出个臭虫来", " 什么仁儿都有", "昆虫", "无");
		Saying saying390 = new Saying("屎壳郎坐飞机", " 臭气熏天；一步登天", "昆虫", "无");
		Saying saying391 = new Saying("苍蝇钻到瓶瓶里", " 处处碰壁", "昆虫", "无");
		Saying saying392 = new Saying("鸡屎蚊子打哈欠", " 好大的气魄", "昆虫", "无");
		Saying saying393 = new Saying("屎壳郎跌炉灶", " 凶多吉少", "昆虫", "无");
		Saying saying394 = new Saying("苍蝇叮大粪", " 臭味相投", "昆虫", "无");
		Saying saying395 = new Saying("蚂蚁进牢房", " 自有出路", "昆虫", "无");
		Saying saying396 = new Saying("屎壳郎戴花", " 臭美", "昆虫", "无");
		Saying saying397 = new Saying("吃蜂蜜说好话", " 甜言蜜语", "昆虫", "无");
		Saying saying398 = new Saying("蚂蚁扛大树", " 不自量", "昆虫", "无");
		Saying saying399 = new Saying("无蜜的蜂房", " 空空洞洞", "昆虫", "无");
		Saying saying400 = new Saying("屎壳郎爬在算盘上", " 混账", "昆虫", "无");
		Saying saying401 = new Saying("钟馗嫁妹", " 鬼混", "人物", "无");
		Saying saying402 = new Saying("潘金莲的信条", " 宁在花下死，做鬼也风流", "人物", "无");
		Saying saying403 = new Saying("阎王桌上抓供果", " 送死", "人物", "无");
		Saying saying404 = new Saying("武大郎捉奸", " 力不能及；反被害了性命", "人物", "无");
		Saying saying405 = new Saying("财神爷戴乌纱帽", " 钱也有，权也有", "人物", "无");
		Saying saying406 = new Saying("张果老骑驴", " 倒着走", "人物", "无");
		Saying saying407 = new Saying("龙王爷出阵", " 翻江倒海", "人物", "无");
		Saying saying408 = new Saying("财神爷招手", " 来福了", "人物", "无");
		Saying saying409 = new Saying("桅杆顶上耍把戏", " 爬得高，跌得重；武艺高", "人物", "无");
		Saying saying410 = new Saying("孙猴子跳出水帘洞", " 好戏在后头", "人物", "无");
		Saying saying411 = new Saying("掀菩萨烧庙宇", " 无恶不作", "人物", "无");
		Saying saying412 = new Saying("武大郎的扁担", " 长不了", "人物", "无");
		Saying saying413 = new Saying("猪八戒戴耳环", " 自以为美", "人物", "无");
		Saying saying414 = new Saying("唐僧遇见白骨精", " 敌我不分", "人物", "无");
		Saying saying415 = new Saying("判官办案", " 吓死人", "人物", "无");
		Saying saying416 = new Saying("猪八戒娶媳妇", " 痴心妄想", "人物", "无");
		Saying saying417 = new Saying("阎王爷好见", " 小鬼难缠", "人物", "无");
		Saying saying418 = new Saying("贾宝玉看林妹妹", " 见如故", "人物", "无");
		Saying saying419 = new Saying("儿子打老子", " 情理难容；无法无天；岂有此理", "人物", "无");
		Saying saying420 = new Saying("阎王爷说谎", " 骗鬼", "人物", "无");
		Saying saying421 = new Saying("孙悟空变山神庙", " 露了尾巴", "人物", "无");
		Saying saying422 = new Saying("孙悟空听见紧箍咒", " 头痛", "人物", "无");
		Saying saying423 = new Saying("才脱了阎王，又撞着小鬼", " 祸不单行", "人物", "无");
		Saying saying424 = new Saying("孙悟空手里的金箍棒", " 随心所欲", "人物", "无");
		Saying saying425 = new Saying("孙悟空保唐僧", " 忠心耿耿；降妖拿怪", "人物", "无");
		Saying saying426 = new Saying("猪八戒招亲", " 黑灯黑人", "人物", "无");
		Saying saying427 = new Saying("孔夫子游列国", " 尽是礼", "人物", "无");
		Saying saying428 = new Saying("悬崖上翻跟头", " 送死；自己找死；凶多吉少", "人物", "无");
		Saying saying429 = new Saying("曹操背时遇蒋干，胡豆背时遇稀饭", " 倒霉透了", "人物", "无");
		Saying saying430 = new Saying("猪八戒抡家伙", " 倒打一耙", "人物", "无");
		Saying saying431 = new Saying("墙上挂钟馗", " 鬼话", "人物", "无");
		Saying saying432 = new Saying("刘备卖草鞋", " 本行", "人物", "无");
		Saying saying433 = new Saying("掖着个孙悟空", " 憋出个猴来", "人物", "无");
		Saying saying434 = new Saying("海龙王吃螃蟹", " 敲骨吸髓", "人物", "无");
		Saying saying435 = new Saying("猪八戒挎腰刀", " 邋遢", "人物", "无");
		Saying saying436 = new Saying("白骨精扮新娘", " 娇里妖气", "人物", "无");
		Saying saying437 = new Saying("教菩萨认字", " 枉费心机", "人物", "无");
		Saying saying438 = new Saying("钱塘江里洗被单", " 大摆布", "人物", "无");
		Saying saying439 = new Saying("钟馗受骗", " 被鬼迷往", "人物", "无");
		Saying saying440 = new Saying("灶王爷跌跟头", " 砸锅了", "人物", "无");
		Saying saying441 = new Saying("灶王爷吹灯", " 好神气", "人物", "无");
		Saying saying442 = new Saying("猪八戒戴眼镜", " 冒充斯文；假斯文", "人物", "无");
		Saying saying443 = new Saying("包公升堂", " 尽管直说", "人物", "无");
		Saying saying444 = new Saying("许仙碰着白娘子", " 天降良缘", "人物", "无");
		Saying saying445 = new Saying("孔夫子偷钱包", " 文明人不做文明事", "人物", "无");
		Saying saying446 = new Saying("猪八戒西天取经", " 三心二意", "人物", "无");
		Saying saying447 = new Saying("菩萨坐冷庙", " 孤苦怜订", "人物", "无");
		Saying saying448 = new Saying("深山小庙的菩萨", " 没见过大香火；没人侍奉；默默无闻", "人物", "无");
		Saying saying449 = new Saying("猪八戒喝磨刀水", " 内秀；秀", "人物", "无");
		Saying saying450 = new Saying("刘备遇孔明", " 如鱼得水", "人物", "无");
		Saying saying451 = new Saying("判官讨饭", " 穷鬼", "人物", "无");
		Saying saying452 = new Saying("张果老倒骑驴", " 背道而驰；不见畜牲面；往后看", "人物", "无");
		Saying saying453 = new Saying("玉帝爷的帽子", " 宝贝疙瘩", "人物", "无");
		Saying saying454 = new Saying("灶王爷打飞脚", " 离板了", "人物", "无");
		Saying saying455 = new Saying("猪八戒啃猪蹄", " 忘了自个儿姓名；自残骨肉", "人物", "无");
		Saying saying456 = new Saying("买鱼放生", " 菩萨心肠", "人物", "无");
		Saying saying457 = new Saying("玻璃菩萨", " 神明", "人物", "无");
		Saying saying458 = new Saying("诸葛亮吊孝", " 假仁假义", "人物", "无");
		Saying saying459 = new Saying("丢下灶王拜山神", " 舍近求远", "人物", "无");
		Saying saying460 = new Saying("二郎神斗孙悟空", " 以变应变；你变我也变", "人物", "无");
		Saying saying461 = new Saying("泥菩萨擂流", " 难过", "人物", "无");
		Saying saying462 = new Saying("假李逵碰真李逵", " 冤家路窄", "人物", "无");
		Saying saying463 = new Saying("判官玩魔术", " 鬼把戏", "人物", "无");
		Saying saying464 = new Saying("当着阎王告判官", " 没有好下场", "人物", "无");
		Saying saying465 = new Saying("黄鼠狼间难卦", " 凶多吉少", "人物", "无");
		Saying saying466 = new Saying("阎王爷做的芝麻饼", " 鬼点子多", "人物", "无");
		Saying saying467 = new Saying("教观音菩萨识字", " 枉费心机", "人物", "无");
		Saying saying468 = new Saying("贾宝玉结婚", " 不是心上人", "人物", "无");
		Saying saying469 = new Saying("挑水骑单车", " 武艺高；本领高", "人物", "无");
		Saying saying470 = new Saying("张飞遇李逵", " 黑对黑；黑上加黑", "人物", "无");
		Saying saying471 = new Saying("诸葛亮用空城计", " 不得己", "人物", "无");
		Saying saying472 = new Saying("吃曹操的饭，想刘备的事", " 人在心不在", "人物", "无");
		Saying saying473 = new Saying("苍蝇叮菩萨", " 没有人味", "人物", "无");
		Saying saying474 = new Saying("猪八戒吃大肉", " 忘本", "人物", "无");
		Saying saying475 = new Saying("白骨精见了孙悟空", " 现原形了", "人物", "无");
		Saying saying476 = new Saying("破庙里的菩萨", " 东倒西歪", "人物", "无");
		Saying saying477 = new Saying("李逵绣花", " 力不能及；力不从心", "人物", "无");
		Saying saying478 = new Saying("孙猴子的屁股", " 坐不住；坐不稳", "人物", "无");
		Saying saying479 = new Saying("孙猴子上天宫", " 得意忘形", "人物", "无");
		Saying saying480 = new Saying("阎王老子谈家常", " 尽讲鬼话", "人物", "无");
		Saying saying481 = new Saying("大年三十的烟火", " 万紫千红", "节气", "无");
		Saying saying482 = new Saying("大年三十晚上熬稀饭", " 年关难过", "节气", "无");
		Saying saying483 = new Saying("月亮坝里掷色子", " 观点模糊", "节气", "无");
		Saying saying484 = new Saying("三十晚上走路", " 没影子", "节气", "无");
		Saying saying485 = new Saying("月亮跟着太阳转", " 沾光；借光", "节气", "无");
		Saying saying486 = new Saying("大年初一拜年", " 你好我也好", "节气", "无");
		Saying saying487 = new Saying("三伏天喝冰水", " 正中下怀", "节气", "无");
		Saying saying488 = new Saying("十五的月亮", " 完美无缺；圆圆满满", "节气", "无");
		Saying saying489 = new Saying("三伏天絮棉袄", " 闲时预备忙时用", "节气", "无");
		Saying saying490 = new Saying("三九天的冰棍", " 没人理", "节气", "无");
		Saying saying491 = new Saying("天亮下大雪", " 明白；明明白白", "节气", "无");
		Saying saying492 = new Saying("三十三颗荞麦九十九道棱", " 一成不变", "节气", "无");
		Saying saying493 = new Saying("月亮下点油灯", " 多事", "节气", "无");
		Saying saying494 = new Saying("元宵里裹爆竹", " 糖衣炮弹", "节气", "无");
		Saying saying495 = new Saying("初七八的月亮", " 半边阴", "节气", "无");
		Saying saying496 = new Saying("大年初一看历书", " 日子长着哩", "节气", "无");
		Saying saying497 = new Saying("踩凳子够月亮", " 差远了", "节气", "无");
		Saying saying498 = new Saying("苞谷面做元宵", " 捏不拢", "节气", "无");
		Saying saying499 = new Saying("三九天穿裙子", " 美丽又动人", "节气", "无");
		Saying saying500 = new Saying("正月十五的月亮", " 光明正大", "节气", "无");
		Saying saying501 = new Saying("三九天的豆腐干", " 冷冰冰，硬邦邦", "节气", "无");
		Saying saying502 = new Saying("叭拉狗咬月亮", " 不知天多高", "节气", "无");
		Saying saying503 = new Saying("三九天扇扇子", " 心里有火", "节气", "无");
		Saying saying504 = new Saying("和尚跟着月亮走", " 借光了", "节气", "无");
		Saying saying505 = new Saying("三九天喝姜汤", " 热心肠", "节气", "无");
		Saying saying506 = new Saying("三伏天的狗", " 喘不上气；上气不接下气", "节气", "无");
		Saying saying507 = new Saying("孩子们过年", " 常盼那一天", "节气", "无");
		Saying saying508 = new Saying("下大雪找蹄印", " 罕见", "节气", "无");
		Saying saying509 = new Saying("大年初一借袍子", " 不识时务；不是时候", "节气", "无");
		Saying saying510 = new Saying("铁拐李看月亮", " 上不正，下参羡", "节气", "无");
		Saying saying511 = new Saying("哈巴狗咬月亮", " 不知天高地厚；不知高低", "节气", "无");
		Saying saying512 = new Saying("年三十夜拨算盘", " 满打满算", "节气", "无");
		Saying saying513 = new Saying("叫花子碰上大雪天", " 饥寒交迫", "节气", "无");
		Saying saying514 = new Saying("太阳和月亮讲话", " 空谈", "节气", "无");
		Saying saying515 = new Saying("过年敲锅盖", " 穷得丁当响", "节气", "无");
		Saying saying516 = new Saying("月亮地里晒被单", " 白搭", "节气", "无");
		Saying saying517 = new Saying("三十晚上借蒸笼", " 不是时候", "节气", "无");
		Saying saying518 = new Saying("大年初一翻皇历", " 头一回；头一遭", "节气", "无");
		Saying saying519 = new Saying("东方天亮下大雪", " 明明白白；明白", "节气", "无");
		Saying saying520 = new Saying("三伏天穿皮袄", " 不是时候，不识时务；乱套了", "节气", "无");
		Saying saying521 = new Saying("端午节拜年", " 不是时候", "节气", "无");
		Saying saying522 = new Saying("三九天掉冰窟", " 抖起来了", "节气", "无");
		Saying saying523 = new Saying("端午节包粽子", " 有棱有角", "节气", "无");
		Saying saying524 = new Saying("穷债户过年", " 躲躲闪闪", "节气", "无");
		Saying saying525 = new Saying("墨汁煮元宵", " 漆黑一团；一团漆黑", "节气", "无");
		Saying saying526 = new Saying("大年初一贴福字", " 吉庆有余", "节气", "无");
		Saying saying527 = new Saying("人过三十不学艺", " 老了", "节气", "无");
		Saying saying528 = new Saying("过年借礼帽", " 不识时务", "节气", "无");
		Saying saying529 = new Saying("大年初一打平伙", " 穷凑合", "节气", "无");
		Saying saying530 = new Saying("纸糊的月亮当太阳", " 偷天换日", "节气", "无");
		Saying saying531 = new Saying("三伏天孵小鸡", " 坏蛋；坏蛋多", "节气", "无");
		Saying saying532 = new Saying("站在河岸捞月亮", " 白搭工", "节气", "无");
		Saying saying533 = new Saying("大年三十喂年猪", " 来不及了", "节气", "无");
		Saying saying534 = new Saying("三九天谈心", " 冷言冷语", "节气", "无");
		Saying saying535 = new Saying("盼望月亮从西出", " 没指望", "节气", "无");
		Saying saying536 = new Saying("拿着碾盘打月亮", " 不知轻重", "节气", "无");
		Saying saying537 = new Saying("天上的月亮", " 看得见，摸不着", "节气", "无");
		Saying saying538 = new Saying("三九天的叫花子", " 又冷又饿", "节气", "无");
		Saying saying539 = new Saying("三伏天的电扇", " 忙得团团转", "节气", "无");
		Saying saying540 = new Saying("晾衣竿钩月亮", " 差天远", "节气", "无");
		Saying saying541 = new Saying("大年三十盼月亮", " 痴心妄想；妄想", "节气", "无");
		Saying saying542 = new Saying("元宵掉进肉锅里", " 说他混蛋，他还心里甜", "节气", "无");
		Saying saying543 = new Saying("三九天桃花开", " 罕见；太离奇；稀奇古怪", "节气", "无");
		Saying saying544 = new Saying("柳条篮子摇元宵", " 滚蛋", "节气", "无");
		Saying saying545 = new Saying("娃娃过年", " 真快活；不操心；光图吃；只讲吃；蹦得欢", "节气", "无");
		Saying saying546 = new Saying("端午节划龙舟", " 载歌载舞", "节气", "无");
		Saying saying547 = new Saying("三伏天的隔夜饭", " 臭货", "节气", "无");
		Saying saying548 = new Saying("年三十夜的年糕", " 人有我有；你有我也有", "节气", "无");
		Saying saying549 = new Saying("月亮坝里耍弯刀", " 明砍", "节气", "无");
		Saying saying550 = new Saying("猴子捞月亮", " 一场空", "节气", "无");
		Saying saying551 = new Saying("举起碾盘打月亮", " 不知天高地厚", "节气", "无");
		Saying saying552 = new Saying("大年初一生娃娃", " 双喜临门", "节气", "无");
		Saying saying553 = new Saying("初一吃十五的饭", " 前吃后空", "节气", "无");
		Saying saying554 = new Saying("爬高梯摘月亮", " 空想", "节气", "无");
		Saying saying555 = new Saying("月亮里的桂树", " 高不可攀", "节气", "无");
		Saying saying556 = new Saying("三九天吃梅子", " 寒酸", "节气", "无");
		Saying saying557 = new Saying("过年的肥猪", " 早晚得杀", "节气", "无");
		Saying saying558 = new Saying("三十年的旧棉絮", " 老套子", "节气", "无");
		Saying saying559 = new Saying("对着月亮攀谈", " 空话连篇", "节气", "无");
		Saying saying560 = new Saying("茶壶里煮元宵", " 满腹心事", "节气", "无");

		dbSManager.insert(saying1);
		dbSManager.insert(saying2);
		dbSManager.insert(saying3);
		dbSManager.insert(saying4);
		dbSManager.insert(saying5);
		dbSManager.insert(saying6);
		dbSManager.insert(saying7);
		dbSManager.insert(saying8);
		dbSManager.insert(saying9);
		dbSManager.insert(saying10);
		dbSManager.insert(saying11);
		dbSManager.insert(saying12);
		dbSManager.insert(saying13);
		dbSManager.insert(saying14);
		dbSManager.insert(saying15);
		dbSManager.insert(saying16);
		dbSManager.insert(saying17);
		dbSManager.insert(saying18);
		dbSManager.insert(saying19);
		dbSManager.insert(saying20);
		dbSManager.insert(saying21);
		dbSManager.insert(saying22);
		dbSManager.insert(saying23);
		dbSManager.insert(saying24);
		dbSManager.insert(saying25);
		dbSManager.insert(saying26);
		dbSManager.insert(saying27);
		dbSManager.insert(saying28);
		dbSManager.insert(saying29);
		dbSManager.insert(saying30);
		dbSManager.insert(saying31);
		dbSManager.insert(saying32);
		dbSManager.insert(saying33);
		dbSManager.insert(saying34);
		dbSManager.insert(saying35);
		dbSManager.insert(saying36);
		dbSManager.insert(saying37);
		dbSManager.insert(saying38);
		dbSManager.insert(saying39);
		dbSManager.insert(saying40);
		dbSManager.insert(saying41);
		dbSManager.insert(saying42);
		dbSManager.insert(saying43);
		dbSManager.insert(saying44);
		dbSManager.insert(saying45);
		dbSManager.insert(saying46);
		dbSManager.insert(saying47);
		dbSManager.insert(saying48);
		dbSManager.insert(saying49);
		dbSManager.insert(saying50);
		dbSManager.insert(saying51);
		dbSManager.insert(saying52);
		dbSManager.insert(saying53);
		dbSManager.insert(saying54);
		dbSManager.insert(saying55);
		dbSManager.insert(saying56);
		dbSManager.insert(saying57);
		dbSManager.insert(saying58);
		dbSManager.insert(saying59);
		dbSManager.insert(saying60);
		dbSManager.insert(saying61);
		dbSManager.insert(saying62);
		dbSManager.insert(saying63);
		dbSManager.insert(saying64);
		dbSManager.insert(saying65);
		dbSManager.insert(saying66);
		dbSManager.insert(saying67);
		dbSManager.insert(saying68);
		dbSManager.insert(saying69);
		dbSManager.insert(saying70);
		dbSManager.insert(saying71);
		dbSManager.insert(saying72);
		dbSManager.insert(saying73);
		dbSManager.insert(saying74);
		dbSManager.insert(saying75);
		dbSManager.insert(saying76);
		dbSManager.insert(saying77);
		dbSManager.insert(saying78);
		dbSManager.insert(saying79);
		dbSManager.insert(saying80);
		dbSManager.insert(saying81);
		dbSManager.insert(saying82);
		dbSManager.insert(saying83);
		dbSManager.insert(saying84);
		dbSManager.insert(saying85);
		dbSManager.insert(saying86);
		dbSManager.insert(saying87);
		dbSManager.insert(saying88);
		dbSManager.insert(saying89);
		dbSManager.insert(saying90);
		dbSManager.insert(saying91);
		dbSManager.insert(saying92);
		dbSManager.insert(saying93);
		dbSManager.insert(saying94);
		dbSManager.insert(saying95);
		dbSManager.insert(saying96);
		dbSManager.insert(saying97);
		dbSManager.insert(saying98);
		dbSManager.insert(saying99);
		dbSManager.insert(saying100);
		dbSManager.insert(saying101);
		dbSManager.insert(saying102);
		dbSManager.insert(saying103);
		dbSManager.insert(saying104);
		dbSManager.insert(saying105);
		dbSManager.insert(saying106);
		dbSManager.insert(saying107);
		dbSManager.insert(saying108);
		dbSManager.insert(saying109);
		dbSManager.insert(saying110);
		dbSManager.insert(saying111);
		dbSManager.insert(saying112);
		dbSManager.insert(saying113);
		dbSManager.insert(saying114);
		dbSManager.insert(saying115);
		dbSManager.insert(saying116);
		dbSManager.insert(saying117);
		dbSManager.insert(saying118);
		dbSManager.insert(saying119);
		dbSManager.insert(saying120);
		dbSManager.insert(saying121);
		dbSManager.insert(saying122);
		dbSManager.insert(saying123);
		dbSManager.insert(saying124);
		dbSManager.insert(saying125);
		dbSManager.insert(saying126);
		dbSManager.insert(saying127);
		dbSManager.insert(saying128);
		dbSManager.insert(saying129);
		dbSManager.insert(saying130);
		dbSManager.insert(saying131);
		dbSManager.insert(saying132);
		dbSManager.insert(saying133);
		dbSManager.insert(saying134);
		dbSManager.insert(saying135);
		dbSManager.insert(saying136);
		dbSManager.insert(saying137);
		dbSManager.insert(saying138);
		dbSManager.insert(saying139);
		dbSManager.insert(saying140);
		dbSManager.insert(saying141);
		dbSManager.insert(saying142);
		dbSManager.insert(saying143);
		dbSManager.insert(saying144);
		dbSManager.insert(saying145);
		dbSManager.insert(saying146);
		dbSManager.insert(saying147);
		dbSManager.insert(saying148);
		dbSManager.insert(saying149);
		dbSManager.insert(saying150);
		dbSManager.insert(saying151);
		dbSManager.insert(saying152);
		dbSManager.insert(saying153);
		dbSManager.insert(saying154);
		dbSManager.insert(saying155);
		dbSManager.insert(saying156);
		dbSManager.insert(saying157);
		dbSManager.insert(saying158);
		dbSManager.insert(saying159);
		dbSManager.insert(saying160);
		dbSManager.insert(saying161);
		dbSManager.insert(saying162);
		dbSManager.insert(saying163);
		dbSManager.insert(saying164);
		dbSManager.insert(saying165);
		dbSManager.insert(saying166);
		dbSManager.insert(saying167);
		dbSManager.insert(saying168);
		dbSManager.insert(saying169);
		dbSManager.insert(saying170);
		dbSManager.insert(saying171);
		dbSManager.insert(saying172);
		dbSManager.insert(saying173);
		dbSManager.insert(saying174);
		dbSManager.insert(saying175);
		dbSManager.insert(saying176);
		dbSManager.insert(saying177);
		dbSManager.insert(saying178);
		dbSManager.insert(saying179);
		dbSManager.insert(saying180);
		dbSManager.insert(saying181);
		dbSManager.insert(saying182);
		dbSManager.insert(saying183);
		dbSManager.insert(saying184);
		dbSManager.insert(saying185);
		dbSManager.insert(saying186);
		dbSManager.insert(saying187);
		dbSManager.insert(saying188);
		dbSManager.insert(saying189);
		dbSManager.insert(saying190);
		dbSManager.insert(saying191);
		dbSManager.insert(saying192);
		dbSManager.insert(saying193);
		dbSManager.insert(saying194);
		dbSManager.insert(saying195);
		dbSManager.insert(saying196);
		dbSManager.insert(saying197);
		dbSManager.insert(saying198);
		dbSManager.insert(saying199);
		dbSManager.insert(saying200);
		dbSManager.insert(saying201);
		dbSManager.insert(saying202);
		dbSManager.insert(saying203);
		dbSManager.insert(saying204);
		dbSManager.insert(saying205);
		dbSManager.insert(saying206);
		dbSManager.insert(saying207);
		dbSManager.insert(saying208);
		dbSManager.insert(saying209);
		dbSManager.insert(saying210);
		dbSManager.insert(saying211);
		dbSManager.insert(saying212);
		dbSManager.insert(saying213);
		dbSManager.insert(saying214);
		dbSManager.insert(saying215);
		dbSManager.insert(saying216);
		dbSManager.insert(saying217);
		dbSManager.insert(saying218);
		dbSManager.insert(saying219);
		dbSManager.insert(saying220);
		dbSManager.insert(saying221);
		dbSManager.insert(saying222);
		dbSManager.insert(saying223);
		dbSManager.insert(saying224);
		dbSManager.insert(saying225);
		dbSManager.insert(saying226);
		dbSManager.insert(saying227);
		dbSManager.insert(saying228);
		dbSManager.insert(saying229);
		dbSManager.insert(saying230);
		dbSManager.insert(saying231);
		dbSManager.insert(saying232);
		dbSManager.insert(saying233);
		dbSManager.insert(saying234);
		dbSManager.insert(saying235);
		dbSManager.insert(saying236);
		dbSManager.insert(saying237);
		dbSManager.insert(saying238);
		dbSManager.insert(saying239);
		dbSManager.insert(saying240);
		dbSManager.insert(saying241);
		dbSManager.insert(saying242);
		dbSManager.insert(saying243);
		dbSManager.insert(saying244);
		dbSManager.insert(saying245);
		dbSManager.insert(saying246);
		dbSManager.insert(saying247);
		dbSManager.insert(saying248);
		dbSManager.insert(saying249);
		dbSManager.insert(saying250);
		dbSManager.insert(saying251);
		dbSManager.insert(saying252);
		dbSManager.insert(saying253);
		dbSManager.insert(saying254);
		dbSManager.insert(saying255);
		dbSManager.insert(saying256);
		dbSManager.insert(saying257);
		dbSManager.insert(saying258);
		dbSManager.insert(saying259);
		dbSManager.insert(saying260);
		dbSManager.insert(saying261);
		dbSManager.insert(saying262);
		dbSManager.insert(saying263);
		dbSManager.insert(saying264);
		dbSManager.insert(saying265);
		dbSManager.insert(saying266);
		dbSManager.insert(saying267);
		dbSManager.insert(saying268);
		dbSManager.insert(saying269);
		dbSManager.insert(saying270);
		dbSManager.insert(saying271);
		dbSManager.insert(saying272);
		dbSManager.insert(saying273);
		dbSManager.insert(saying274);
		dbSManager.insert(saying275);
		dbSManager.insert(saying276);
		dbSManager.insert(saying277);
		dbSManager.insert(saying278);
		dbSManager.insert(saying279);
		dbSManager.insert(saying280);
		dbSManager.insert(saying281);
		dbSManager.insert(saying282);
		dbSManager.insert(saying283);
		dbSManager.insert(saying284);
		dbSManager.insert(saying285);
		dbSManager.insert(saying286);
		dbSManager.insert(saying287);
		dbSManager.insert(saying288);
		dbSManager.insert(saying289);
		dbSManager.insert(saying290);
		dbSManager.insert(saying291);
		dbSManager.insert(saying292);
		dbSManager.insert(saying293);
		dbSManager.insert(saying294);
		dbSManager.insert(saying295);
		dbSManager.insert(saying296);
		dbSManager.insert(saying297);
		dbSManager.insert(saying298);
		dbSManager.insert(saying299);
		dbSManager.insert(saying300);
		dbSManager.insert(saying301);
		dbSManager.insert(saying302);
		dbSManager.insert(saying303);
		dbSManager.insert(saying304);
		dbSManager.insert(saying305);
		dbSManager.insert(saying306);
		dbSManager.insert(saying307);
		dbSManager.insert(saying308);
		dbSManager.insert(saying309);
		dbSManager.insert(saying310);
		dbSManager.insert(saying311);
		dbSManager.insert(saying312);
		dbSManager.insert(saying313);
		dbSManager.insert(saying314);
		dbSManager.insert(saying315);
		dbSManager.insert(saying316);
		dbSManager.insert(saying317);
		dbSManager.insert(saying318);
		dbSManager.insert(saying319);
		dbSManager.insert(saying320);
		dbSManager.insert(saying321);
		dbSManager.insert(saying322);
		dbSManager.insert(saying323);
		dbSManager.insert(saying324);
		dbSManager.insert(saying325);
		dbSManager.insert(saying326);
		dbSManager.insert(saying327);
		dbSManager.insert(saying328);
		dbSManager.insert(saying329);
		dbSManager.insert(saying330);
		dbSManager.insert(saying331);
		dbSManager.insert(saying332);
		dbSManager.insert(saying333);
		dbSManager.insert(saying334);
		dbSManager.insert(saying335);
		dbSManager.insert(saying336);
		dbSManager.insert(saying337);
		dbSManager.insert(saying338);
		dbSManager.insert(saying339);
		dbSManager.insert(saying340);
		dbSManager.insert(saying341);
		dbSManager.insert(saying342);
		dbSManager.insert(saying343);
		dbSManager.insert(saying344);
		dbSManager.insert(saying345);
		dbSManager.insert(saying346);
		dbSManager.insert(saying347);
		dbSManager.insert(saying348);
		dbSManager.insert(saying349);
		dbSManager.insert(saying350);
		dbSManager.insert(saying351);
		dbSManager.insert(saying352);
		dbSManager.insert(saying353);
		dbSManager.insert(saying354);
		dbSManager.insert(saying355);
		dbSManager.insert(saying356);
		dbSManager.insert(saying357);
		dbSManager.insert(saying358);
		dbSManager.insert(saying359);
		dbSManager.insert(saying360);
		dbSManager.insert(saying361);
		dbSManager.insert(saying362);
		dbSManager.insert(saying363);
		dbSManager.insert(saying364);
		dbSManager.insert(saying365);
		dbSManager.insert(saying366);
		dbSManager.insert(saying367);
		dbSManager.insert(saying368);
		dbSManager.insert(saying369);
		dbSManager.insert(saying370);
		dbSManager.insert(saying371);
		dbSManager.insert(saying372);
		dbSManager.insert(saying373);
		dbSManager.insert(saying374);
		dbSManager.insert(saying375);
		dbSManager.insert(saying376);
		dbSManager.insert(saying377);
		dbSManager.insert(saying378);
		dbSManager.insert(saying379);
		dbSManager.insert(saying380);
		dbSManager.insert(saying381);
		dbSManager.insert(saying382);
		dbSManager.insert(saying383);
		dbSManager.insert(saying384);
		dbSManager.insert(saying385);
		dbSManager.insert(saying386);
		dbSManager.insert(saying387);
		dbSManager.insert(saying388);
		dbSManager.insert(saying389);
		dbSManager.insert(saying390);
		dbSManager.insert(saying391);
		dbSManager.insert(saying392);
		dbSManager.insert(saying393);
		dbSManager.insert(saying394);
		dbSManager.insert(saying395);
		dbSManager.insert(saying396);
		dbSManager.insert(saying397);
		dbSManager.insert(saying398);
		dbSManager.insert(saying399);
		dbSManager.insert(saying400);
		dbSManager.insert(saying401);
		dbSManager.insert(saying402);
		dbSManager.insert(saying403);
		dbSManager.insert(saying404);
		dbSManager.insert(saying405);
		dbSManager.insert(saying406);
		dbSManager.insert(saying407);
		dbSManager.insert(saying408);
		dbSManager.insert(saying409);
		dbSManager.insert(saying410);
		dbSManager.insert(saying411);
		dbSManager.insert(saying412);
		dbSManager.insert(saying413);
		dbSManager.insert(saying414);
		dbSManager.insert(saying415);
		dbSManager.insert(saying416);
		dbSManager.insert(saying417);
		dbSManager.insert(saying418);
		dbSManager.insert(saying419);
		dbSManager.insert(saying420);
		dbSManager.insert(saying421);
		dbSManager.insert(saying422);
		dbSManager.insert(saying423);
		dbSManager.insert(saying424);
		dbSManager.insert(saying425);
		dbSManager.insert(saying426);
		dbSManager.insert(saying427);
		dbSManager.insert(saying428);
		dbSManager.insert(saying429);
		dbSManager.insert(saying430);
		dbSManager.insert(saying431);
		dbSManager.insert(saying432);
		dbSManager.insert(saying433);
		dbSManager.insert(saying434);
		dbSManager.insert(saying435);
		dbSManager.insert(saying436);
		dbSManager.insert(saying437);
		dbSManager.insert(saying438);
		dbSManager.insert(saying439);
		dbSManager.insert(saying440);
		dbSManager.insert(saying441);
		dbSManager.insert(saying442);
		dbSManager.insert(saying443);
		dbSManager.insert(saying444);
		dbSManager.insert(saying445);
		dbSManager.insert(saying446);
		dbSManager.insert(saying447);
		dbSManager.insert(saying448);
		dbSManager.insert(saying449);
		dbSManager.insert(saying450);
		dbSManager.insert(saying451);
		dbSManager.insert(saying452);
		dbSManager.insert(saying453);
		dbSManager.insert(saying454);
		dbSManager.insert(saying455);
		dbSManager.insert(saying456);
		dbSManager.insert(saying457);
		dbSManager.insert(saying458);
		dbSManager.insert(saying459);
		dbSManager.insert(saying460);
		dbSManager.insert(saying461);
		dbSManager.insert(saying462);
		dbSManager.insert(saying463);
		dbSManager.insert(saying464);
		dbSManager.insert(saying465);
		dbSManager.insert(saying466);
		dbSManager.insert(saying467);
		dbSManager.insert(saying468);
		dbSManager.insert(saying469);
		dbSManager.insert(saying470);
		dbSManager.insert(saying471);
		dbSManager.insert(saying472);
		dbSManager.insert(saying473);
		dbSManager.insert(saying474);
		dbSManager.insert(saying475);
		dbSManager.insert(saying476);
		dbSManager.insert(saying477);
		dbSManager.insert(saying478);
		dbSManager.insert(saying479);
		dbSManager.insert(saying480);
		dbSManager.insert(saying481);
		dbSManager.insert(saying482);
		dbSManager.insert(saying483);
		dbSManager.insert(saying484);
		dbSManager.insert(saying485);
		dbSManager.insert(saying486);
		dbSManager.insert(saying487);
		dbSManager.insert(saying488);
		dbSManager.insert(saying489);
		dbSManager.insert(saying490);
		dbSManager.insert(saying491);
		dbSManager.insert(saying492);
		dbSManager.insert(saying493);
		dbSManager.insert(saying494);
		dbSManager.insert(saying495);
		dbSManager.insert(saying496);
		dbSManager.insert(saying497);
		dbSManager.insert(saying498);
		dbSManager.insert(saying499);
		dbSManager.insert(saying500);
		dbSManager.insert(saying501);
		dbSManager.insert(saying502);
		dbSManager.insert(saying503);
		dbSManager.insert(saying504);
		dbSManager.insert(saying505);
		dbSManager.insert(saying506);
		dbSManager.insert(saying507);
		dbSManager.insert(saying508);
		dbSManager.insert(saying509);
		dbSManager.insert(saying510);
		dbSManager.insert(saying511);
		dbSManager.insert(saying512);
		dbSManager.insert(saying513);
		dbSManager.insert(saying514);
		dbSManager.insert(saying515);
		dbSManager.insert(saying516);
		dbSManager.insert(saying517);
		dbSManager.insert(saying518);
		dbSManager.insert(saying519);
		dbSManager.insert(saying520);
		dbSManager.insert(saying521);
		dbSManager.insert(saying522);
		dbSManager.insert(saying523);
		dbSManager.insert(saying524);
		dbSManager.insert(saying525);
		dbSManager.insert(saying526);
		dbSManager.insert(saying527);
		dbSManager.insert(saying528);
		dbSManager.insert(saying529);
		dbSManager.insert(saying530);
		dbSManager.insert(saying531);
		dbSManager.insert(saying532);
		dbSManager.insert(saying533);
		dbSManager.insert(saying534);
		dbSManager.insert(saying535);
		dbSManager.insert(saying536);
		dbSManager.insert(saying537);
		dbSManager.insert(saying538);
		dbSManager.insert(saying539);
		dbSManager.insert(saying540);
		dbSManager.insert(saying541);
		dbSManager.insert(saying542);
		dbSManager.insert(saying543);
		dbSManager.insert(saying544);
		dbSManager.insert(saying545);
		dbSManager.insert(saying546);
		dbSManager.insert(saying547);
		dbSManager.insert(saying548);
		dbSManager.insert(saying549);
		dbSManager.insert(saying550);
		dbSManager.insert(saying551);
		dbSManager.insert(saying552);
		dbSManager.insert(saying553);
		dbSManager.insert(saying554);
		dbSManager.insert(saying555);
		dbSManager.insert(saying556);
		dbSManager.insert(saying557);
		dbSManager.insert(saying558);
		dbSManager.insert(saying559);
		dbSManager.insert(saying560);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
