package com.example.demo.push;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.StringUtil;
import com.oppo.push.server.Notification;
import com.oppo.push.server.Result;
import com.oppo.push.server.Sender;
import com.oppo.push.server.Target;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TestOppoPush {
    private static int appId = 30261266;
    private static String appKey = "fc8e845db29e459ea698b0a13184812d";
    private static String appSecret = "46e58bd3f430470495e82d41e688f2fe";
    private static String masterSecret = "20f7da9377a54c88844ffc157d22dd0d";

    private static String packageName = "com.yingke.dimapp";
    private static String appName = "dimapp";
    private static Boolean soundFlag = true;
    private static String deviceId = "OPPO_CN_cbed9338cdf62308d8769ca7c354592d";


    public static void main(String[] args) {
        String businessType = "MSG_PUSH_TASK";
        String businessType1 = "MSG_NO_PUSH_TASK";
        String businessType2 = "MSG_ACCIDENT_VERIFY_PASS";
        String businessType3 = "MSG_APP_REPAIR_CAR";
        send(businessType);
    }

    private static void send(String businessType) {
        //{"statusCode":200,"reason":"OK","returnCode":{"code":0,"message":"Success"},"messageId":"30261266-1-1-615002573be09603b2613826"}
        //{"statusCode":200,"reason":"OK","returnCode":{"code":0,"message":"Success"},"messageId":"30261266-1-1-615033fd897619ec1297db89"}
        Sender sender = null;
        try {
            sender = new Sender(appKey, masterSecret);
            // 创建通知栏消息体
            MBAppPushMessageEO messageEO = CommonPushUtil.createMessage(businessType, "android");
            Notification notification = mGetNotification(messageEO);
            // 创建发送对象
            Target target = Target.build(deviceId);
            // 发送单推消息
            System.out.println(JSONObject.toJSONString(notification));

            Result result = sender.unicastNotification(notification, target);
            System.out.println(JSONObject.toJSONString(result));
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private static Notification mGetNotification(MBAppPushMessageEO messageEo) {
        Notification notification = new Notification();
    /**
    * 以下参数必填项
    */
        notification.setTitle(messageEo.getTitle());
        notification.setContent(messageEo.getContent());
    /**
    * 以下参数非必填项， 如果需要使用可以参考OPPO push服务端api文档进行设置
    */
        //通知栏样式 1. 标准样式  2. 长文本样式  3. 大图样式 【非必填，默认1-标准样式】
        notification.setStyle(2);
        // App开发者自定义消息Id，OPPO推送平台根据此ID做去重处理，对于广播推送相同appMessageId只会保存一次，对于单推相同appMessageId只会推送一次
        notification.setAppMessageId(UUID.randomUUID().toString());

        // 应用接收消息到达回执的回调URL，字数限制200以内，中英文均以一个计算
        notification.setCallBackUrl("https://xxx/msgcallback/oppo");
        // App开发者自定义回执参数，字数限制50以内，中英文均以一个计算
        notification.setCallBackParameter("xxxOppo888");

        // 点击动作类型0，启动应用；1，打开应用内页（activity的intent action）；2，打开网页；4，打开应用内页（activity）；【非必填，默认值为0】;5,Intent scheme URL
        notification.setClickActionType(0);
        // 应用内页地址【click_action_type为1或4时必填，长度500】
        //    notification.setClickActionActivity(packageName+".main.oppoPush.ThirdPopupPushActivity");
        // 网页地址【click_action_type为2必填，长度500】
        //notification.setClickActionUrl("http://www.test.com");
        // 动作参数，打开应用内页或网页时传递给应用或网页【JSON格式，非必填】，字符数不能超过4K，示例：{"key1":"value1","key2":"value2"}
        //notification.setActionParameters("{\"key1\":\"value1\",\"key2\":\"value2\"}");
        notification.setActionParameters(getExtParameters(messageEo, soundFlag));
        // 展示类型 (0, “即时”),(1, “定时”)
        notification.setShowTimeType(0);
        /*// 定时展示开始时间（根据time_zone转换成当地时间），时间的毫秒数
         notification.setShowStartTime(System.currentTimeMillis() + 1000 * 60 * 3);
        // 定时展示结束时间（根据time_zone转换成当地时间），时间的毫秒数
       notification.setShowEndTime(System.currentTimeMillis() + 1000 * 60 * 5);*/
        // 是否进离线消息,【非必填，默认为True】
        // notification.setOffLine(true);
        // 离线消息的存活时间(time_to_live) (单位：秒), 【off_line值为true时，必填，最长3天】
        //    notification.setOffLineTtl(24 * 3600);
        // 时区，默认值：（GMT+08:00）北京，香港，新加坡
        //notification.setTimeZone("GMT+08:00");
        // 0：不限联网方式, 1：仅wifi推送
        notification.setNetworkType(0);
        if (soundFlag) {
            notification.setChannelId("sound_oppo");
        }
        notification.setCallBackUrl("https://xxx:18443/dim-app/msgcallback/oppo");
        return notification;
    }

    private static Map<String, String> getExtParameters(MBAppPushMessageEO messageEo) {
        Map<String, String> map = new HashMap();
        String businessType = messageEo.getBusinessType();
        String businessNo = messageEo.getBusinessNo();
        String messageId = messageEo.getMessageId() + "";
        String businessData = messageEo.getBusinessData();
        String content = messageEo.getContent();

        map.put("businessType", businessType);
        map.put("businessNo", businessNo);
        map.put("messageId", messageId);
        if (StringUtil.isNotBlank(businessData)) {
            map.put("businessData", businessData);
        }
        map.put("content", content);
        return map;
    }

    private static String getExtParameters(MBAppPushMessageEO messageEo, Boolean soundFlag) {
        String businessType = messageEo.getBusinessType();
        String businessNo = messageEo.getBusinessNo();
        String messageId = messageEo.getMessageId() + "";
        String businessData = messageEo.getBusinessData();
        String content = messageEo.getContent();

        JSONObject notice = new JSONObject();

        //Android声音播放辅助设置
        if (("MSG_PUSH_TASK".equals(businessType) || "MSG_PUSH_TASK_AGAIN".equals(businessType)) && soundFlag) {
            notice.put("androidMusic", "pushrepair.mp3");
        }

        notice.put("businessType", businessType);
        notice.put("businessNo", businessNo);
        notice.put("messageId", messageId);
        if (StringUtil.isNotBlank(businessData)) {
            notice.put("businessData", businessData);
        }
        notice.put("content", content);

        JSONObject androidData = new JSONObject();
        androidData.put("androidData", notice);

        return JSONObject.toJSONString(androidData);
    }
}
