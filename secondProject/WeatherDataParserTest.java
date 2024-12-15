package basicProjectII.secondProject;


import java.util.List;

public class WeatherDataParserTest {
    public static void main(String[] args) {
        WeatherApiCaller apiCaller = new WeatherApiCaller();
        WeatherDataParser dataParser = new WeatherDataParser();

        String baseDate = "20241213"; // 나중에 현재 날짜 기준으로 잡으면 됨 전날 것으로 하면 에러 걸림
        String baseTimeUltraSrtNcst = "1536"; // 초단기실황 기준 시간 // 나중에 현재 시간 기준으로 잡으면 됨
        String baseTimeVilageFcst = "1400";  // 단기예보 기준 시간 // 나중에 현재 시간 기준으로 잡으면됨
        int nx = 55; //
        int ny = 127;

        try {
            // 1. 초단기실황 데이터 확인
            System.out.println("=== 초단기실황 데이터 확인 ===");
            String ultraSrtNcstResponse = apiCaller.fetchWeatherData("ULTRA_SRT_NCST", baseDate, baseTimeUltraSrtNcst, nx, ny);

            List<WeatherResponse.Item> ultraSrtNcstData = dataParser.parseAndFilterWeatherData(ultraSrtNcstResponse, "ULTRA_SRT_NCST");

            if (ultraSrtNcstData.isEmpty()) {
                System.out.println("초단기실황 파싱된 데이터가 없습니다.");
            } else {
                System.out.println("초단기실황 파싱된 데이터:");
                ultraSrtNcstData.forEach(System.out::println);
            }

            // 2. 단기예보 데이터 확인
            System.out.println("\n=== 단기예보 데이터 확인 ===");
            String vilageFcstResponse = apiCaller.fetchWeatherData("VILAGE_FCST", baseDate, baseTimeVilageFcst, nx, ny);

            List<WeatherResponse.Item> vilageFcstData = dataParser.parseAndFilterWeatherData(vilageFcstResponse, "VILAGE_FCST");

            if (vilageFcstData.isEmpty()) {
                System.out.println("단기예보 파싱된 데이터가 없습니다.");
            } else {
                System.out.println("단기예보 파싱된 데이터:");
                vilageFcstData.forEach(System.out::println);
            }

            // 3. 초단기예보 데이터 확인
            System.out.println("\n=== 초단기예보 데이터 확인 ===");
            String ulterSrtFcstResponse = apiCaller.fetchWeatherData("ULTRA_SRT_FCST", baseDate, baseTimeVilageFcst, nx, ny);

            List<WeatherResponse.Item> ulterSrtFcstData = dataParser.parseAndFilterWeatherData(ulterSrtFcstResponse, "ULTRA_SRT_FCST");

            if (ulterSrtFcstData.isEmpty()) {
                System.out.println("초단기예보 파싱된 데이터가 없습니다.");
            } else {
                System.out.println("초단기예보 파싱된 데이터:");
                ulterSrtFcstData.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("테스트 중 오류 발생: " + e.getMessage());
        }
    }
}
