package com.heulwen.sparkmindsdemoapp.services.impl;

import com.heulwen.sparkmindsdemoapp.services.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateServiceImpl implements DateService {
    @Override
    public Map<String, Object> getDateComparisonData() {
        Map<String, Object> dataMap = new HashMap<>();

        // ===== 1. LEGACY API (Calendar/Date) =====
        Calendar cal = Calendar.getInstance();
        cal.set(2026, 0, 1); // Month = 0 (Tháng 1)
        Date originalDate = cal.getTime();

        // Minh họa tính Mutable (Dễ gây lỗi side-effect)
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date modifiedDate = cal.getTime();
        int month = cal.get(Calendar.MONTH); // Vẫn là 0-based

        // ===== 2. MODERN API (java.time) =====
        LocalDate localDate = LocalDate.of(2026, 1, 1); // Month = 1 rõ ràng
        LocalDate plus7 = localDate.plusDays(7);

        // Kiểm tra tính Immutable (Bất biến)
        // plusDays trả về object mới, không thay đổi object cũ
        boolean isSameObject = (localDate == plus7);

        // ===== 3. TIMESTAMP & METADATA =====
        long timestamp = new Date().getTime();

        // Đưa dữ liệu vào Map
        dataMap.put("originalDate", originalDate);
        dataMap.put("modifiedDate", modifiedDate);
        dataMap.put("month", month);
        dataMap.put("localDate", localDate);
        dataMap.put("plus7", plus7);
        dataMap.put("isSameObject", isSameObject);
        dataMap.put("timestamp", timestamp);

        return dataMap;
    }
}
