package com.example.forecast.bean;

import java.util.List;

public class WeatherBean {

    /**
     * error : 0
     * status : success
     * date : 2020-08-02
     * results : [{"currentCity":"泉州","pm25":"50","index":[{"des":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","tipt":"穿衣指数","title":"穿衣","zs":"炎热"},{"des":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。","tipt":"洗车指数","title":"洗车","zs":"较不宜"},{"des":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","tipt":"感冒指数","title":"感冒","zs":"少发"},{"des":"天气较好，但因风力较强，在户外要选择合适的运动；另外考虑到高温天气，建议停止高强度运动。","tipt":"运动指数","title":"运动","zs":"较不宜"},{"des":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。","tipt":"紫外线强度指数","title":"紫外线强度","zs":"很强"}],"weather_data":[{"date":"周日 08月02日 (实时：29℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"南风3-4级","temperature":"35 ~ 26℃"},{"date":"周一","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"小雨","wind":"东南风3-4级","temperature":"34 ~ 26℃"},{"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/dayu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"大雨转小雨","wind":"南风4-5级","temperature":"30 ~ 26℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"小雨转多云","wind":"南风4-5级","temperature":"31 ~ 26℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    private List<ResultsBean> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * currentCity : 泉州
         * pm25 : 50
         * index : [{"des":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","tipt":"穿衣指数","title":"穿衣","zs":"炎热"},{"des":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。","tipt":"洗车指数","title":"洗车","zs":"较不宜"},{"des":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","tipt":"感冒指数","title":"感冒","zs":"少发"},{"des":"天气较好，但因风力较强，在户外要选择合适的运动；另外考虑到高温天气，建议停止高强度运动。","tipt":"运动指数","title":"运动","zs":"较不宜"},{"des":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。","tipt":"紫外线强度指数","title":"紫外线强度","zs":"很强"}]
         * weather_data : [{"date":"周日 08月02日 (实时：29℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"南风3-4级","temperature":"35 ~ 26℃"},{"date":"周一","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"小雨","wind":"东南风3-4级","temperature":"34 ~ 26℃"},{"date":"周二","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/dayu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"大雨转小雨","wind":"南风4-5级","temperature":"30 ~ 26℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"小雨转多云","wind":"南风4-5级","temperature":"31 ~ 26℃"}]
         */

        private String currentCity;
        private String pm25;
        private List<IndexBean> index;
        private List<WeatherDataBean> weather_data;

        public String getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(String currentCity) {
            this.currentCity = currentCity;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<WeatherDataBean> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherDataBean> weather_data) {
            this.weather_data = weather_data;
        }

        public static class IndexBean {
            /**
             * des : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             * tipt : 穿衣指数
             * title : 穿衣
             * zs : 炎热
             */

            private String des;
            private String tipt;
            private String title;
            private String zs;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getTipt() {
                return tipt;
            }

            public void setTipt(String tipt) {
                this.tipt = tipt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getZs() {
                return zs;
            }

            public void setZs(String zs) {
                this.zs = zs;
            }
        }

        public static class WeatherDataBean {
            /**
             * date : 周日 08月02日 (实时：29℃)
             * dayPictureUrl : http://api.map.baidu.com/images/weather/day/qing.png
             * nightPictureUrl : http://api.map.baidu.com/images/weather/night/qing.png
             * weather : 晴
             * wind : 南风3-4级
             * temperature : 35 ~ 26℃
             */

            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String weather;
            private String wind;
            private String temperature;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayPictureUrl() {
                return dayPictureUrl;
            }

            public void setDayPictureUrl(String dayPictureUrl) {
                this.dayPictureUrl = dayPictureUrl;
            }

            public String getNightPictureUrl() {
                return nightPictureUrl;
            }

            public void setNightPictureUrl(String nightPictureUrl) {
                this.nightPictureUrl = nightPictureUrl;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
