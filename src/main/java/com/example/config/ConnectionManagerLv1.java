package com.example.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManagerLv1 {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConnectionManagerLv1.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties 파일을 찾을 수 없습니다. 경로 또는 파일명을 확인하세요.");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("DB 설정 파일 로딩 중 예외 발생", e);
        }
    }

    public static Connection getConnection() {
        try {
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String pass = properties.getProperty("db.password");

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();  // ← 실제 원인을 출력
            throw new RuntimeException("❗ DB 연결 실패: 설정 정보 또는 서버 상태를 확인하세요.", e);
        }
    }
}
