package basicProjectII.secondProject;

import java.util.List;

public class WeatherResponse {
    private List<Item> items; // 날씨 데이터 항목 리스트

    // Getter & Setter for items
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // 내부 클래스 Item
    public static class Item {
        private String baseDate; // 기준 날짜
        private String baseTime; // 기준 시간
        private String category; // 데이터 유형 (T1H, PTY 등)
        private String fcstDate; // 예보 날짜
        private String fcstTime; // 예보 시간
        private float obsrValue; // 초단기실황 관측 값
        private float fcstValue; // 단기예보, 초단기예보 관측 값
        private int nx; // X 좌표
        private int ny; // Y 좌표

        // Getter & Setter
        public String getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(String baseDate) {
            this.baseDate = baseDate;
        }

        public String getBaseTime() {
            return baseTime;
        }

        public void setBaseTime(String baseTime) {
            this.baseTime = baseTime;
        }

        public String getFcstDate(){
            return fcstDate;
        }

        public void setFcstDate(String fcstDate){
            this.fcstDate = fcstDate;
        }

        public String getFcstTime(){
            return fcstTime;
        }

        public void setFcstTime(String fcstTime){
            this.fcstTime = fcstTime;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public float getObsrValue() {
            return obsrValue;
        }

        public void setObsrValue(float obsrValue) {
            this.obsrValue = obsrValue;
        }

        public float getFcstValue() {
            return fcstValue;
        }

        public void setFcstValue(float fcstValue) {
            this.fcstValue = fcstValue;
        }

        public int getNx() {
            return nx;
        }

        public void setNx(int nx) {
            this.nx = nx;
        }

        public int getNy() {
            return ny;
        }

        public void setNy(int ny) {
            this.ny = ny;
        }

        @Override
        public String toString() {
            if (fcstDate != null && fcstTime != null) {
                // 단기예보, 초단기예보 데이터 포맷
                return "Item{" +
                        "baseDate='" + baseDate + '\'' +
                        ", baseTime='" + baseTime + '\'' +
                        "category='" + category + '\'' +
                        ", fcstDate='" + fcstDate + '\'' +
                        ", fcstTime='" + fcstTime + '\'' +
                        ", fcstValue='" + fcstValue + '\'' +
                        ", nx=" + nx +
                        ", ny=" + ny +
                        '}';
            } else {
                // 초단기실황 데이터 포맷
                return "Item{" +
                        "baseDate='" + baseDate + '\'' +
                        ", baseTime='" + baseTime + '\'' +
                        ", category='" + category + '\'' +
                        ", obsrValue='" + obsrValue + '\'' +
                        ", nx=" + nx +
                        ", ny=" + ny +
                        '}';
            }
        }

    }
}
