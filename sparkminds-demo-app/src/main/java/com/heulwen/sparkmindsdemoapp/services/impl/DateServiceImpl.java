package com.heulwen.sparkmindsdemoapp.services.impl;

import com.heulwen.sparkmindsdemoapp.services.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateServiceImpl implements DateService {
    @Override
    public Map<String, Object> getDateComparisonData() {
        Map<String, Object> dataMap = new HashMap<>();

        // ===== 1. LOCALDATE (Chỉ Ngày) =====
        // Dùng để biểu diễn một ngày không có thời gian và không có múi giờ (VD: Sinh nhật, Ngày nghỉ)
        LocalDate localDate = LocalDate.now();
        LocalDate nextWeek = localDate.plusWeeks(1);

        // ===== 2. LOCALDATETIME (Ngày + Giờ) =====
        // Dùng để biểu diễn ngày và giờ, nhưng KHÔNG có múi giờ.
        // Nó phụ thuộc vào đồng hồ cục bộ của hệ thống đang chạy.
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime nextFewHours = localDateTime.plusHours(5).plusMinutes(30);

        // ===== 3. ZONEDDATETIME (Ngày + Giờ + Múi Giờ) =====
        // Biểu diễn ngày giờ chính xác tại một khu vực địa lý cụ thể. Rất quan trọng cho ứng dụng quốc tế.
        ZonedDateTime zonedDateTimeVN = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        // Chuyển đổi cùng một thời điểm đó sang múi giờ Tokyo (Nhật Bản đi trước VN 2 tiếng)
        ZonedDateTime zonedDateTimeTokyo = zonedDateTimeVN.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

        // Format để hiển thị ZonedDateTime cho dễ nhìn trên UI
        DateTimeFormatter zdtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        // Đưa dữ liệu vào Map
        dataMap.put("localDate", localDate);
        dataMap.put("nextWeek", nextWeek);

        dataMap.put("localDateTime", localDateTime);
        dataMap.put("nextFewHours", nextFewHours);

        dataMap.put("zonedDateTimeVN", zonedDateTimeVN.format(zdtFormatter));
        dataMap.put("zonedDateTimeTokyo", zonedDateTimeTokyo.format(zdtFormatter));

        return dataMap;
    }
}