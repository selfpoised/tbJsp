package com.taobao.pamirs.schedule;
import org.springframework.core.JdkVersion;

public class JDJdkVersion {

    /**
     * Constant identifying the 1.5 JVM (Java 5).
     */
    public static final int JAVA_15 = 2;


    /**
     * Convenience method to determine if the current JVM is at least
     * Java 1.5 (Java 5).
     * @return <code>true</code> if the current JVM is at least Java 1.5
     * @see #getMajorJavaVersion()
     * @see #JAVA_15
     * @see #JAVA_16
     * @see #JAVA_17
     */
    public static boolean isAtLeastJava15() {
        return JdkVersion.getMajorJavaVersion() >= JAVA_15;
    }




}
