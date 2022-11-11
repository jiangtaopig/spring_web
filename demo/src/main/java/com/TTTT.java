package com;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TTTT {
    public static void main(String[] args) {
        String data = "{\n" +
                " \n" +
                "  \"content\": [\n" +
                "    \n" +
                "    {\n" +
                "      \"viewAttr\": {\n" +
                "        \"type\": \"label\",\n" +
                "        \"textList\": [\"车牌号\", \"#licenseNo#\"],\n" +
                "        \"titleColor\": \"0xff666768\",\n" +
                "        \"textColor\": \"0xff333333\"\n" +
                "      },\n" +
                "      \"params\": null,\n" +
                "      \"actions\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"viewAttr\": {\n" +
                "        \"options\": [\n" +
                "          {\"code\": \"上汽大众威然\", \"title\": \"上汽大众威然\"},\n" +
                "          {\"code\": \"上汽大众辉昂\", \"title\": \"上汽大众辉昂\"},\n" +
                "         \n" +
                "        ],\n" +
                "        \"type\": \"title_content_item\",\n" +
                "        \"content\": \"请选择车系\",\n" +
                "        \"title\": \"车系\"\n" +
                "      },\n" +
                "      \"params\": [\n" +
                "        {},\n" +
                "        {\"value\": \"#seriesName#\", \"key\": \"seriesName\"}\n" +
                "      ],\n" +
                "      \"actions\": [\n" +
                "        {\n" +
                "          \"titleName\": \"title\",\n" +
                "          \"type\": \"showPicker\",\n" +
                "          \"url\": \"app/vsvOrder/updateSeriesName\",\n" +
                "          \"arguments\": {\"dealerCode\": \"#dealerCode#\", \"orderNo\": \"#orderNo#\"}\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"viewAttr\": {\"type\": \"divider\"},\n" +
                "      \"params\": null,\n" +
                "      \"actions\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"viewAttr\": {\n" +
                "        \"expandStatus\": \"true\",\n" +
                "        \"textList\": [\"维保信息\"],\n" +
                "        \"titleColor\": \"0xff060606\",\n" +
                "        \"titleFontWeight\": \"bold\",\n" +
                "        \"type\": \"label\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"viewAttr\": {\n" +
                "        \"src\": \"images/dealer_service/modify.png\",\n" +
                "        \"type\": \"input_config_row\",\n" +
                "        \"textAlign\": \"left\",\n" +
                "        \"titleColor\": \"0xff666768\",\n" +
                "        \"title\": \"维保结算单号\",\n" +
                "        \"content\":\"#partnerOrderNo#\"\n" +
                "      },\n" +
                "      \"params\": [\n" +
                "        {},\n" +
                "        {\"key\": \"settleNo\", \"value\": \"#partnerOrderNo#\"}\n" +
                "      ],\n" +
                "      \"actions\": [\n" +
                "        {\n" +
                "          \"type\": \"submit\",\n" +
                "          \"url\": \"app/vsvOrder/updateSettle\",\n" +
                "          \"arguments\": {\"dealerCode\": \"#dealerCode#\", \"orderNo\": \"#orderNo#\"}\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "\n" +
                "    {\n" +
                "      \"viewAttr\": {\n" +
                "        \"expandStatus\": \"true\",\n" +
                "        \"textList\": [\"订单信息\"],\n" +
                "        \"titleColor\": \"0xff060606\",\n" +
                "        \"titleFontWeight\": \"bold\",\n" +
                "        \"type\": \"label\"\n" +
                "      },\n" +
                "      \"actions\": [\n" +
                "        {\"expandFlag\": \"orderData\", \"type\": \"expandable\"}\n" +
                "      ]\n" +
                "    }\n" +
                "    \n" +
                "  ]\n" +
                "}";

        String tt = "{\n" +
                "      \"viewAttr\": {\n" +
                "        \"type\": \"label\",\n" +
                "        \"textList\": [\"维保结算单号\", \"#partnerOrderNo#\"],\n" +
                "        \"titleColor\": \"0xff666768\",\n" +
                "        \"textColor\": \"0xff333333\"\n" +
                "      },\n" +
                "      \"params\": null,\n" +
                "      \"actions\": null\n" +
                "    }";

        JSONObject weibao = JSONObject.parseObject(tt);

        JSONObject templateRow = JSONObject.parseObject(data);
        if (templateRow.containsKey("content")) {

            JSONArray jsonArray = templateRow.getJSONArray("content");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = (JSONObject) jsonArray.get(i);
                JSONObject attr = item.getJSONObject("viewAttr");
                if ("维保结算单号".equals(attr.getString("title"))) {
                    System.out.println("jjj");
                    jsonArray.set(i, weibao);
                }
            }

            System.out.println("============ size = " + jsonArray.size());
            System.out.println("jason = " + JSONObject.toJSONString(templateRow));
        } else {
            System.out.println("xxxxxx");
        }
    }
}
