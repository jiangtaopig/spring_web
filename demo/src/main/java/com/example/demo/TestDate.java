package com.example.demo;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDate {

    @Test
    public void test() throws ParseException {
        LocalDate date1 = LocalDate.parse("2023-03-11");
        LocalDate date2 = LocalDate.parse("2022-01-01");

        Period period = Period.between(date2, date1);
        System.out.println(period);
        int days = period.getDays();
        System.out.println(" days = " + days);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
//        String ss = dateTimeFormatter.


        String dd = getThisMonth();
        System.out.println(">>>>>>>>>>>>>>>>" + dd);

        Date date = getCurrentFirstOfYear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = simpleDateFormat.format(date);
        System.out.println("time >>> " + time);

        Date year = getPastYear(date, -1);
        System.out.println("year ====" + simpleDateFormat.format(year));

        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        String td_st_str = today_end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("td_st_str = " + td_st_str);

//        Date date3 = simpleDateFormat.parse(td_st_str);

        Date date3 = Date.from(today_end.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("====" + simpleDateFormat.format(date3));


        Date start = getTodayStart();

        System.out.println("start ====" + simpleDateFormat.format(start));

        Date afterDate = getPastDate(start, -1);
        System.out.println("afterDate ====" + simpleDateFormat.format(afterDate));

        Date monthBegin = getMonthBegin();
        System.out.println("monthBegin ====" + simpleDateFormat.format(monthBegin));

        Date monthEnd = getMonthEnd();
        System.out.println("monthEnd ====" + simpleDateFormat.format(monthEnd));

        String ddd = "2023-12-01 00:00:00";
        Date date4 = simpleDateFormat.parse(ddd);

        Date afterMonth = getPastMonth(date4, -1);
        System.out.println("afterMonth ====" + simpleDateFormat.format(afterMonth));

        String sss = null;
        sss.length();

    }

    public String getStackTraceInfo(Exception e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ex) {
            return "printStackTrace()转换错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }

    @Test
    public void ttt() {

        try {
            int b = 2/0;
        }catch (Exception e) {
            System.out.println(getStackTraceInfo(e));
            System.out.println("---------------");
            System.out.println(e);
        }

        String url = "https://mdssit.insaic.com:22443/hybird#/charts/warranty?ac=ICQQQD12ID&{userCode:enUserCode}";
        String a = _getCommonReportUrl(url);
        System.out.println(" a = " + a);

        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.addAndGet(2);

        String imageUrl = "https://yk-ecs-test.oss-cn-shanghai-finance-1-pub.aliyuncs.com/ecs/export/writeoff/2023/03/21/BK20370/XFile1679365541756554.jpg?Expires=1679368268&OSSAccessKeyId=STS.NTn5MzxFuF3zWgt9mZXX22Af3&Signature=4GHF%2F2MFDRs5mZZC00PgfKJHHU4%3D&security-token=CAISigJ1q6Ft5B2yfSjIr5fbfvfOlZlU8fGRVUHF3W0PVNce3YTN0Tz2IHpIfHVrCeEZv%2FQ2nGtZ6%2FYdlrBzWpVfRECBatBrq51M6h6kbs%2Fatteu7LsC0DgM95ILU0yV5tTbRsmkZvG%2FE67fRjKpvyt3xqSABlfGdle5MJqPpId6Z9AMJGeRZiZHA9EkTWkL6rVtVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDugXi3zn%2BCk7JN%2Fdmgfcj8Mpc3ZM8lCO3YhrImKvDztwdL8AVP%2BatMi6hJxCzKpNn1ASMKuUneb7GEo4I%2BfFYhPflnS%2FFe1v%2FnjrhpuanenJ%2By1xtWIaRPTDRw6C9LBXB5chqAASne9cVJDgVh4yCc7lXix6QbPvGtRqTGDtbUegUFVpqwDpb42SJZgzZr2Z2JOEIRMNTC6BG20eby4CWfGj%2FMLoQGFWB36s1bq%2Be7NXBT169GTxqHj8Ejnvz2s%2BlgL4Fikts4n8z6qudlpZbWijKIgQmvWPBoYRHVD7FolDBqw3va";

        String imageUrl2 = imageUrl;

        if (!imageUrl.startsWith("ecs/export")) {
            int startIndex = imageUrl.indexOf("ecs/export");
            imageUrl = imageUrl.substring(startIndex, imageUrl.indexOf("?", startIndex));
             // 在进入第二步的时候，由于该图片是暂存的，可能没有AI标识，所以调用 ai 校验，但是url是拼接后的，有问题，要截取。
        }
        System.out.println("imageUrl >>>"+imageUrl);

        String urlPath = getOssUrlPath(imageUrl2);
        System.out.println("urlPath = "+urlPath);
    }



    public String getOssUrlPath(String ossFullUrl){
//        String ossFullurl = "https://insaic-app-test.oss-cn-shanghai-finance-1-pub.aliyuncs.com/app/sop/2021/12/1.jpg?Expires=1642576689&OSSAccessKeyId=STS.NU5Xub8YkmdtxBBYa4foHvKcL&Signature=jHkDEHLD3WxptgOS%2FqCWHeyahN8%3D&security-token=CAISjgJ1q6Ft5B2yfSjIr5aAE8%2FW1YZK2qafemTzvWFhauBkmY7Irjz2IHFMf3FsBe8bs%2Fo3mm1R6%2FYelrBzWpVfRECBatBrq51M6h6kbs%2Fatteu7LsC0GsEo4kcTEyV5tTbRsmkZvG%2FE67fRjKpvyt3xqSABlfGdle5MJqPpId6Z9AMJGeRZiZHA9EkTWkL6rVtVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDugXi3zn%2BCk7JN%2Fdmgfcj8Mpc3ZM8lCO3YhrImKvDztwdL8AVP%2BatMi6hJxCzKpNn1ASMKsk3da7aIrYAyclcnO%2FFnS%2FJe0v%2Fnjrh5vPfalo%2Bywg5VeP1YSDiaXoewb2dr8yYSi7wagAFS%2Fg67UjmV61FP%2Bj%2BJbY1qRX6Z%2BCqyt0UD3TYyEMggAgif2O1bZ0mIUdBKXDGB0fLDMjgOrn0adpuKlxrrtdMxq6tjqcZEuKfnJsiJa35EBwg7OylJfch%2BY7Tgy9F3tDu0xrEUvnK84LRz2fNdxVCOgKzFOcv%2Bxmz2YS0TGxO3GQ%3D%3D";
        if(ossFullUrl != null){
            try{
                URL url = new URL(ossFullUrl);
                String path = url.getPath();
                String query = url.getQuery();
                //转换后：ecs/export/writeoff/VW01240/20200407/5672090a9a3af5968bde4866fc002740.jpg
                return path.substring(1);
            }catch (Exception e){
                //logger.error("ossFullurl is invalid!");
                //上传图片地址非URL时原地址返回
                return ossFullUrl;
            }
        }
        return ossFullUrl;
    }


    /**
     * 生成报表url
     * @param originUrl 例：https://mdssit.insaic.com:22443/hybird#/charts/warranty?{userCode:enUserCode}
     * @return
     */
    public  String _getCommonReportUrl(String originUrl) {
        String reportUrl = originUrl;
        int startIndex = originUrl.indexOf("{");
        int endIndex = originUrl.indexOf("}");
        if (startIndex > -1 && endIndex > -1) {
            String domainUrl = originUrl.substring(0, startIndex);
            String paramStr = originUrl.substring(startIndex+1, endIndex);
            String[] params = paramStr.split(",");
            String queryParams = "";
            for (String paramCondition : params) {
                String[] conditionP  = paramCondition.split(":");
                String key = conditionP[0];
                String valueKey = conditionP[1];
                String value =  "1234";///_getReportParamValueByKey(valueKey, appCode);
                if (StringUtils.isEmpty(queryParams)) {
                    queryParams = String.format("%s=%s", key, value);
                } else {
                    queryParams = queryParams.concat(String.format("&%s=%s", key, value));
                }
            }
            reportUrl = domainUrl.concat(queryParams);
        }
        return reportUrl;
    }


    public static Date getMonthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return date;
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定日期所在月份结束的时间
     * @return
     */
    public static Date getMonthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return date;
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void testForDate() {
        //规定返回日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = sf.format(gcLast.getTime());
        //打印本月第一天
        System.out.println(day_first);
    }

    //2、获取当月最后一天
    @Test
    public void testForDatelast() {
        //获取Calendar
        Calendar calendar = Calendar.getInstance();
        //设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
        //设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String ss = sf.format(calendar.getTime());
        System.out.println(ss + " 23:59:59");
    }


    /**
     * 获取今天的开始时间 2023-03-17 00:00:00
     *
     * @return
     */
    public static Date getTodayStart() {
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return Date.from(today_start.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取今天的结束时间 2023-03-17 23:59:59
     *
     * @return
     */
    public static Date getTodayEnd() {
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return Date.from(today_end.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date 之前或之后多少天 ，负数表示之后多少天，
     * @param date
     * @param past
     * @return
     */
    public static Date getPastDate(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date date2 = calendar.getTime();
        return date2;
    }

    public static Date getPastMonth(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - past);
        Date date2 = calendar.getTime();
        return date2;
    }

    public static Date getPastYear(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - past);
        Date date2 = calendar.getTime();
        return date2;
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrentFirstOfYear() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getFirstOfYear(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getFirstOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }


    public String getThisMonth() {
        // 获取前月的第一天
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String firstDay = format.format(calendar.getTime());
        return firstDay;
    }

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2023-03-02 10:18:47";
        Date dd = simpleDateFormat.parse(dateStr);

        boolean isOutTime = judgeValidTime(dd);
        System.out.println("isOutTime >>> " + isOutTime);
    }

    /**
     * 判断输入的时间是不是比当前时间晚30分钟，如果晚的话返回 false
     */
    public boolean judgeValidTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -30);//dev和sit设置30分钟有效
//        calendar.add(Calendar.DATE,1);
        Date invalidTime = calendar.getTime();

        String dd = simpleDateFormat.format(invalidTime);
        System.out.println("dd >>> " + dd);

        return date.after(invalidTime);
    }
}
