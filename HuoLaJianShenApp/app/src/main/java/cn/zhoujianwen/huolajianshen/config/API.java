package cn.zhoujianwen.huolajianshen.config;


/**
 * Created by zhoujianwen.cn on 2018/05/28.
 */
public class API {


    public static final String SERVER_URI = "http://39.108.113.63:8080/HuoLaJianShenServer/%s";
    public static final String SERVER_DEBUG_URI = "http://39.108.113.63:8080/HuoLaJianShenServer/%s";

    public static final String SERVER_IMAGE_URL = "http://play.mengtta.com/";
    public static final String DEBUG_SERVER_IMAGE_URL = "http://192.168.0.183/";

    /*一：注册手机uuid*/
   public static final String REGISTER_UUID_URI ="UuidServlet";

   /*二：登陆*/
   public static final String LOGIN_URI ="LoginServlet";

    /*四：首页数据*/
   public static final String MAIN_URI ="HomeServlet?userId=%d";

    /*八：新增一条目标记录*/
   public static final String INSTER_GOAL_URI="GoalInfoServlet";

    /*九：获取训练计划信息*/
   public static final String GET_GOAL_PLAN_URI="GoalInfoServlet?userId=%d&goalStatus=%d";

   /*十三：获取消耗纪录*/
   public static final String SUBMIT_CONSUME_RECORD_URI ="ConsumeRecordServlet?";

    /*十三：获取消耗纪录*/
   public static final String CONSUME_RECORD_URI ="ConsumeRecordServlet?userId=%d&page=%d";

    /*十八：第一条身材记录*/
   public static final String FIRST_RECORD_FIGUTR_URI ="FirstGoalRecordServlet?userId=%d&goalRecordType=%d";

    /*十九：提交身材记录*/
   public static final String SUBMIT_FIGUTR_URI ="GoalRecordServlet";

    /*二十：修改计划完成度*/
   public static final String MODIFY_GOAL_COMPLETE_URI ="ModifyGoalStutusServlet";

    /*二十一：修改基本数据*/
   public static final String MODIFY_BASE_DATA_URI ="BodyDataServlet";

    /*二十二：得到身体数据*/
   public static final String BODY_DATA_URI ="BodyDataServlet?userId=%d";

    /*二十三：版本更新*/
    public static final String LATEST_VERSION_CHECK_URI = "VersionUpdateServlet";
}
