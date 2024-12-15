package com.sparta.logistics.hub.libs.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        // 테이블 클리어
        jdbcTemplate.execute("TRUNCATE TABLE public.p_hub RESTART IDENTITY CASCADE");
        System.out.println("✅ p_hub table cleared!");

        // SQL 쿼리 실행
        String sql = """
        INSERT INTO public.p_hub ( 
            hub_id, name, address, address_detail, latitude, longitude, 
            created_at, created_by, updated_at, updated_by, is_deleted
        ) VALUES
        (gen_random_uuid(), '서울특별시 센터', '서울특별시 송파구 잠실동 22', '서울특별시 송파구 잠실동 22', 37.51422, 127.106, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '경기 북부 센터', '경기도 고양시 덕양구 권율대로 570', '경기도 고양시 덕양구 행신동 941', 37.61248, 126.8365, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '경기 남부 센터', '경기도 이천시 덕평로 257-21', '경기도 이천시 마장면 덕평리 694-1', 37.21012, 127.3637, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '부산광역시 센터', '부산 동구 중앙대로 206', '부산광역시 동구 초량동 1200-1', 35.1153, 129.0404, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '대구광역시 센터', '대구 북구 태평로 161', '대구광역시 북구 칠성동2가 302-155', 35.88154, 128.5945, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '인천광역시 센터', '인천 남동구 정각로 29', '인천광역시 남동구 구월동 1139', 37.44834, 126.7316, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '광주광역시 센터', '광주 서구 내방로 111', '광주광역시 서구 치평동 1200', 35.15152, 126.8513, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '대전광역시 센터', '대전 서구 둔산로 100', '대전광역시 서구 둔산동 1500', 36.35179, 127.3789, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '울산광역시 센터', '울산 남구 중앙로 201', '울산광역시 남구 삼산동 1487-1', 35.53838, 129.3114, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '세종특별자치시 센터', '세종특별자치시 한누리대로 2130', '세종특별자치시 나성동 736', 36.48021, 127.2596, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '강원특별자치도 센터', '강원특별자치도 춘천시 중앙로 1', '강원특별자치도 춘천시 봉의동 69', 37.88122, 127.7297, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '충청북도 센터', '충북 청주시 상당구 상당로 82', '충청북도 청주시 상당구 남문로2가 115', 36.63316, 127.4905, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '충청남도 센터', '충남 홍성군 홍북읍 충남대로 21', '충청남도 홍성군 홍북읍 상하리 1046', 36.65657, 126.6703, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '전북특별자치도 센터', '전북특별자치도 전주시 완산구 효자로 225', '전라북도 전주시 완산구 효자동1가 177', 35.82033, 127.1054, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '전라남도 센터', '전남 무안군 삼향읍 오룡길 1', '전라남도 무안군 삼향읍 남악리 262', 34.80753, 126.4629, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '경상북도 센터', '경북 안동시 풍천면 도청대로 455', '경상북도 안동시 풍천면 갈전리 1480', 36.56815, 128.7259, 
            NOW(), 'master', NOW(), 'master', FALSE),
        (gen_random_uuid(), '경상남도 센터', '경남 창원시 의창구 중앙대로 300', '경상남도 창원시 의창구 용호동 7-4', 35.22754, 128.6811, 
            NOW(), 'master', NOW(), 'master', FALSE);
        """;

        jdbcTemplate.execute(sql);
        System.out.println("✅ Initial data inserted into p_hub table!");
    }
}
