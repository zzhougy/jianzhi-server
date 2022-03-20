package com.zhou.jianzhi.common.util;


public class CodeStringGenerator {
    /** 开始时间截 (2018-08-01) */
    private static long twepoch = 1533052800000L;

    /** 机器ID所占的位数 */
    private static long workerIdBits = 1L;

    /** 数据标识ID所占的位数 */
    private static long datacenterIdBits = 1L;

    /** 支持的最大机器ID，为31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识ID，为31 */
    private static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在ID中占的位数 */
    private static long sequenceBits = 4L;

    /** 机器ID向左移10位 */
    private static long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(10+4) */
    private static long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移18位(4+4+10) */
    private static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为1023 */
    private static long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private static long workerId;

    /** 数据中心ID(0~31) */
    private static long datacenterId;

    /** 毫秒内序列(0~4095) */
    private static long sequence = 0L;

    /** 上次生成id的时间截 */
    private static long lastTimestamp = -1L;


    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return ID
     */
    public static synchronized String nextId(String prefix) {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        long value = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
        if(prefix != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(prefix);
            sb.append(value);
            return sb.toString();
        }else {
            return String.valueOf(value);
        }
    }

    /**
     * 获得下一个ID
     *
     * @return ID
     */
    public static synchronized String nextId(String prefix, long workerId, long datacenterId) {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        long value = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(value);
        return sb.toString();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp
     *            上次生成id的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }
    public static void main(String[] args) {
        System.out.println(CodeStringGenerator.nextId("test", 1, 1));
        System.out.println(CodeStringGenerator.nextId("acee"));

    }
}
