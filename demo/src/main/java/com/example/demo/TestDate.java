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
