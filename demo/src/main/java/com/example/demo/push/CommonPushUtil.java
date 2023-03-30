package com.example.demo.push;

public class CommonPushUtil {
    public static MBAppPushMessageEO createMessage(String messageType,String platform){
        String title = "";
        String content = "";
        String businessType = "";
        String businessNo = "";
        String messageId =  "";
        String businessData = "";

        if("MSG_PUSH_TASK".equals(messageType)){


            title = "【上汽保险】推修任务";
            content = "【人保财险】 ,xxxx, 发生人伤类事故, 出险时间：2022-07-11 23:54:38, 出险地点：阿克苏市明珠路和幸福路交汇处, 客户xx ";
            businessType = "MSG_PUSH_TASK";
            businessNo = String.valueOf(20160027);
            messageId =  String.valueOf(232709983);
            businessData = "{\"licenseNo\":\"xxxx\",\"receiver\":\"pxq123456\",\"dealerCode\":\"VW00606\",\"label\":\"人伤\",\"insurerCode\":\"picc\"}";

            /*title = "【上汽保险】推修任务";
            content = "【太保财险】 ,吉A8BX99, 北京现代BH7181PAY轿车, 发生多车类事故, 出险时间：2022-08-23 07:05:32, 出险地点：阳光花园[九台-九台区], 客户万红梅";
            businessType = "MSG_PUSH_TASK";
            businessNo = String.valueOf(20854108);
            messageId =  String.valueOf(246783774);
            businessData = "{\"licenseNo\":\"吉A8BX99\",\"receiver\":\"BK22975RP01\",\"dealerCode\":\"BK22975\",\"label\":\"多车\",\"insurerCode\":\"cpic\"}";

*/
        }else if("MSG_NO_PUSH_TASK".equals(messageType)){
            title = "【上汽保险】未推修线索";
            content = "您有一条未推修线索,【人保财险】 ,2021-09-10 15:57:16 车辆苏A8UIK0, 在上海市上海市闵行区龙茗路/漕宝路(路口)发生事故, 客户WWQ--,  请知悉! ";
            businessType = "MSG_NO_PUSH_TASK";
            //businessNo = String.valueOf(8080);
            //messageId =  String.valueOf(6474978);

            businessNo = String.valueOf(8476082);
            messageId =  String.valueOf(139898955);

            businessData = "{\"licenseNo\":\"苏A8UIK0\",\"receiver\":\"shiguche\",\"dealerCode\":\"VW00798\",\"insurerCode\":\"picc\"}";
        }else if("MSG_ACCIDENT_VERIFY_PASS".equals(messageType)){
            title = "事故车核损通过";
            content = "您跟进的粤B8KC88事故车，保司已经核损通过，核损通过金额1945.00，是否更新维修完成信息？";
            businessType = "MSG_ACCIDENT_VERIFY_PASS";
            businessNo = String.valueOf(17564);
            messageId =  String.valueOf(784865);
            businessData = "{\"licenseNo\":\"粤B8KC88\",\"receiver\":\"cs1\",\"dealerCode\":\"BK14734\",\"verifyAmount\":1945.00}";
        }else if("MSG_APP_REPAIR_CAR".equals(messageType)){
            title = "售后线索进店提醒";
            content = "事故车沪A94422已到店，请更新为事故车已到店";
            businessType = "MSG_APP_REPAIR_CAR";
            businessNo = String.valueOf(20042);
            messageId =  String.valueOf(6025676);
            businessData = "{\"licenseNo\":\"沪A94422\",\"receiver\":\"cs8\",\"dealerCode\":\"BK20370\",\"arrivalTime\":\"2021-06-03 21:06:32\"}";
        }else if("MSG_ASSESSMENT_CLOSED".equals(messageType)){
            title = "案件结案通知";
            content = "中国太保在2021-09-26 16:42:07接受报案的鄂A3AGL3已经结案。请关注。";
            businessType = "MSG_ASSESSMENT_CLOSED";
            businessNo = "CPICDS00000004292";
            messageId =  String.valueOf(6572362);
            businessData = "{\"businessNo\":\"CPICDS00000004292\",\"assessmentNo\":\"CPICDS00000004292\",\"receiver\":\"shiguche\",\"plateNo\":\"鄂A3AGL3\",\"dealerCode\":\"AS21976\",\"insurer\":\"cpic\",\"vin\":\"ZJE8ERZ4GHBBMNDUA\",\"reportNo\":\"C310100VEH21008895\"}";
        }else if("MSG_NOTIE_TIP".equals(messageType)){
            title = "通知";
            content = "请关注。";
            businessType = "MSG_NOTIE_TIP";
            businessNo = String.valueOf(18970612);
            messageId =  String.valueOf(206613074);
            businessData = "{\"url\":\"http:www.baidu.com\",\"receiver\":\"2450109LK\",\"remark\":\"通知！\"}";

        }
        MBAppPushMessageEO messageEo = new MBAppPushMessageEO();
        messageEo.setTitle(title);
        messageEo.setContent(content);
        messageEo.setBusinessType(businessType);
        messageEo.setBusinessNo(businessNo);
        messageEo.setBusinessData(businessData);
        messageEo.setMessageId(messageId);
        messageEo.setPlatform(platform);
        return messageEo;
    }
}
