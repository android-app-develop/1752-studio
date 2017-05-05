package com.tencent.tool;

import com.tencent.tauth.Tencent;

public class TecentConstants {
    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    //public static final String APP_ID = "wxd930ea5d5a258f4f";

    public static final String APP_ID="222222";//222222//
    //申请的开发appid
    public static final String WX_APP_ID = "wxd32e5f7d98f46d37";
    
    public static final String WX_ACTION = "action";
    
    public static final String WX_ACTION_INVITE = "invite";
    
    public static final String WX_RESULT_CODE = "ret";
    
    public static final String WX_RESULT_MSG = "msg";
    
    public static final String WX_RESULT = "result";
    
    public static class ShowMsgActivity {
        public static final String STitle = "showmsg_title";
        public static final String SMessage = "showmsg_message";
        public static final String BAThumbData = "showmsg_thumb_data";
        
    }
    
    public static Tencent mTencent;
    public static final String shareTargetUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.bingo.joy";
	public static final String shareImgUrl = "http://qd.poms.baidupcs.com/file/76a49981f9c9c687ddea498bedbcf62d?bkt=p3-140076a49981f9c9c687ddea498bedbcf62d7f6f6b47000000002107&fid=3844243459-250528-118834024956237&time=1449667435&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-Am31UnDY1VxMCCTqjgjWXpSHSpY%3D&to=qb&fm=Nan,B,G,t&sta_dx=0&sta_cs=0&sta_ft=png&sta_ct=0&fm2=Nanjing02,B,G,t&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=140076a49981f9c9c687ddea498bedbcf62d7f6f6b47000000002107&sl=78184526&expires=8h&rt=sh&r=482375267&mlogid=7952624629680018319&vuk=3844243459&vbdid=660022180&fin=joy108x108.png&fn=joy108x108.png";
	public static final String localImgUrl = "/storage/emulated/0/joy.png";
}
