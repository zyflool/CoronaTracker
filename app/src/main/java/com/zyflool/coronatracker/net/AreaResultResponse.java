package com.zyflool.coronatracker.net;

import java.util.List;

public class AreaResultResponse {

    /**
     * results : [{"locationId":130000,"continentName":"亚洲","continentEnglishName":"Asia","countryName":"中国","countryEnglishName":"China","countryFullName":null,"provinceName":"河北省","provinceEnglishName":"Hebei","provinceShortName":"河北","currentConfirmedCount":17,"confirmedCount":346,"suspectedCount":0,"curedCount":323,"deadCount":6,"comment":"","cities":[{"cityName":"保定","currentConfirmedCount":15,"confirmedCount":47,"suspectedCount":0,"curedCount":32,"deadCount":0,"locationId":130600,"cityEnglishName":"Baoding"},{"cityName":"张家口","currentConfirmedCount":2,"confirmedCount":43,"suspectedCount":0,"curedCount":41,"deadCount":0,"locationId":130700,"cityEnglishName":"Zhangjiakou"},{"cityName":"沧州","currentConfirmedCount":1,"confirmedCount":49,"suspectedCount":0,"curedCount":45,"deadCount":3,"locationId":130900,"cityEnglishName":"Cangzhou"},{"cityName":"唐山","currentConfirmedCount":0,"confirmedCount":58,"suspectedCount":0,"curedCount":57,"deadCount":1,"locationId":130200,"cityEnglishName":"Tangshan"},{"cityName":"邯郸","currentConfirmedCount":0,"confirmedCount":32,"suspectedCount":0,"curedCount":32,"deadCount":0,"locationId":130400,"cityEnglishName":"Handan"},{"cityName":"廊坊","currentConfirmedCount":0,"confirmedCount":30,"suspectedCount":0,"curedCount":30,"deadCount":0,"locationId":131000,"cityEnglishName":"Langfang"},{"cityName":"石家庄","currentConfirmedCount":0,"confirmedCount":29,"suspectedCount":0,"curedCount":29,"deadCount":0,"locationId":130100,"cityEnglishName":"Shijiazhuang"},{"cityName":"邢台","currentConfirmedCount":0,"confirmedCount":23,"suspectedCount":0,"curedCount":22,"deadCount":1,"locationId":130500,"cityEnglishName":"Xingtai"},{"cityName":"秦皇岛","currentConfirmedCount":0,"confirmedCount":10,"suspectedCount":0,"curedCount":9,"deadCount":1,"locationId":130300,"cityEnglishName":"Qinhuangdao"},{"cityName":"境外输入","currentConfirmedCount":0,"confirmedCount":10,"suspectedCount":0,"curedCount":10,"deadCount":0,"locationId":0},{"cityName":"衡水","currentConfirmedCount":0,"confirmedCount":8,"suspectedCount":0,"curedCount":8,"deadCount":0,"locationId":131100,"cityEnglishName":"Hengshui"},{"cityName":"承德","currentConfirmedCount":0,"confirmedCount":7,"suspectedCount":0,"curedCount":7,"deadCount":0,"locationId":130800,"cityEnglishName":"Chengde"},{"cityName":"待明确","currentConfirmedCount":-1,"confirmedCount":0,"suspectedCount":0,"curedCount":1,"deadCount":0,"locationId":0}],"updateTime":1592873091511}]
     * success : true
     */

    private boolean success;
    private List<ResultsBean> results;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * locationId : 130000
         * continentName : 亚洲
         * continentEnglishName : Asia
         * countryName : 中国
         * countryEnglishName : China
         * countryFullName : null
         * provinceName : 河北省
         * provinceEnglishName : Hebei
         * provinceShortName : 河北
         * currentConfirmedCount : 17
         * confirmedCount : 346
         * suspectedCount : 0
         * curedCount : 323
         * deadCount : 6
         * comment :
         * cities : [{"cityName":"保定","currentConfirmedCount":15,"confirmedCount":47,"suspectedCount":0,"curedCount":32,"deadCount":0,"locationId":130600,"cityEnglishName":"Baoding"},{"cityName":"张家口","currentConfirmedCount":2,"confirmedCount":43,"suspectedCount":0,"curedCount":41,"deadCount":0,"locationId":130700,"cityEnglishName":"Zhangjiakou"},{"cityName":"沧州","currentConfirmedCount":1,"confirmedCount":49,"suspectedCount":0,"curedCount":45,"deadCount":3,"locationId":130900,"cityEnglishName":"Cangzhou"},{"cityName":"唐山","currentConfirmedCount":0,"confirmedCount":58,"suspectedCount":0,"curedCount":57,"deadCount":1,"locationId":130200,"cityEnglishName":"Tangshan"},{"cityName":"邯郸","currentConfirmedCount":0,"confirmedCount":32,"suspectedCount":0,"curedCount":32,"deadCount":0,"locationId":130400,"cityEnglishName":"Handan"},{"cityName":"廊坊","currentConfirmedCount":0,"confirmedCount":30,"suspectedCount":0,"curedCount":30,"deadCount":0,"locationId":131000,"cityEnglishName":"Langfang"},{"cityName":"石家庄","currentConfirmedCount":0,"confirmedCount":29,"suspectedCount":0,"curedCount":29,"deadCount":0,"locationId":130100,"cityEnglishName":"Shijiazhuang"},{"cityName":"邢台","currentConfirmedCount":0,"confirmedCount":23,"suspectedCount":0,"curedCount":22,"deadCount":1,"locationId":130500,"cityEnglishName":"Xingtai"},{"cityName":"秦皇岛","currentConfirmedCount":0,"confirmedCount":10,"suspectedCount":0,"curedCount":9,"deadCount":1,"locationId":130300,"cityEnglishName":"Qinhuangdao"},{"cityName":"境外输入","currentConfirmedCount":0,"confirmedCount":10,"suspectedCount":0,"curedCount":10,"deadCount":0,"locationId":0},{"cityName":"衡水","currentConfirmedCount":0,"confirmedCount":8,"suspectedCount":0,"curedCount":8,"deadCount":0,"locationId":131100,"cityEnglishName":"Hengshui"},{"cityName":"承德","currentConfirmedCount":0,"confirmedCount":7,"suspectedCount":0,"curedCount":7,"deadCount":0,"locationId":130800,"cityEnglishName":"Chengde"},{"cityName":"待明确","currentConfirmedCount":-1,"confirmedCount":0,"suspectedCount":0,"curedCount":1,"deadCount":0,"locationId":0}]
         * updateTime : 1592873091511
         */

        private int locationId;
        private String continentName;
        private String continentEnglishName;
        private String countryName;
        private String countryEnglishName;
        private Object countryFullName;
        private String provinceName;
        private String provinceEnglishName;
        private String provinceShortName;
        private int currentConfirmedCount;    //现存确诊人数
        private int confirmedCount;        //累计确诊人数
        private int suspectedCount;          //疑似感染人数
        private int curedCount;              //累计治愈人数
        private int deadCount;             //累计死亡人数
        private String comment;
        private long updateTime;
        private List<CitiesBean> cities;

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public String getContinentName() {
            return continentName;
        }

        public void setContinentName(String continentName) {
            this.continentName = continentName;
        }

        public String getContinentEnglishName() {
            return continentEnglishName;
        }

        public void setContinentEnglishName(String continentEnglishName) {
            this.continentEnglishName = continentEnglishName;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCountryEnglishName() {
            return countryEnglishName;
        }

        public void setCountryEnglishName(String countryEnglishName) {
            this.countryEnglishName = countryEnglishName;
        }

        public Object getCountryFullName() {
            return countryFullName;
        }

        public void setCountryFullName(Object countryFullName) {
            this.countryFullName = countryFullName;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getProvinceEnglishName() {
            return provinceEnglishName;
        }

        public void setProvinceEnglishName(String provinceEnglishName) {
            this.provinceEnglishName = provinceEnglishName;
        }

        public String getProvinceShortName() {
            return provinceShortName;
        }

        public void setProvinceShortName(String provinceShortName) {
            this.provinceShortName = provinceShortName;
        }

        public int getCurrentConfirmedCount() {
            return currentConfirmedCount;
        }

        public void setCurrentConfirmedCount(int currentConfirmedCount) {
            this.currentConfirmedCount = currentConfirmedCount;
        }

        public int getConfirmedCount() {
            return confirmedCount;
        }

        public void setConfirmedCount(int confirmedCount) {
            this.confirmedCount = confirmedCount;
        }

        public int getSuspectedCount() {
            return suspectedCount;
        }

        public void setSuspectedCount(int suspectedCount) {
            this.suspectedCount = suspectedCount;
        }

        public int getCuredCount() {
            return curedCount;
        }

        public void setCuredCount(int curedCount) {
            this.curedCount = curedCount;
        }

        public int getDeadCount() {
            return deadCount;
        }

        public void setDeadCount(int deadCount) {
            this.deadCount = deadCount;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public List<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(List<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            /**
             * cityName : 保定
             * currentConfirmedCount : 15
             * confirmedCount : 47
             * suspectedCount : 0
             * curedCount : 32
             * deadCount : 0
             * locationId : 130600
             * cityEnglishName : Baoding
             */

            private String cityName;
            private int currentConfirmedCount;    //现存确诊人数
            private int confirmedCount;         //累计确诊人数
            private int suspectedCount;          //疑似感染人数
            private int curedCount;             //累计治愈人数
            private int deadCount;              //累计死亡人数
            private int locationId;
            private String cityEnglishName;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public int getCurrentConfirmedCount() {
                return currentConfirmedCount;
            }

            public void setCurrentConfirmedCount(int currentConfirmedCount) {
                this.currentConfirmedCount = currentConfirmedCount;
            }

            public int getConfirmedCount() {
                return confirmedCount;
            }

            public void setConfirmedCount(int confirmedCount) {
                this.confirmedCount = confirmedCount;
            }

            public int getSuspectedCount() {
                return suspectedCount;
            }

            public void setSuspectedCount(int suspectedCount) {
                this.suspectedCount = suspectedCount;
            }

            public int getCuredCount() {
                return curedCount;
            }

            public void setCuredCount(int curedCount) {
                this.curedCount = curedCount;
            }

            public int getDeadCount() {
                return deadCount;
            }

            public void setDeadCount(int deadCount) {
                this.deadCount = deadCount;
            }

            public int getLocationId() {
                return locationId;
            }

            public void setLocationId(int locationId) {
                this.locationId = locationId;
            }

            public String getCityEnglishName() {
                return cityEnglishName;
            }

            public void setCityEnglishName(String cityEnglishName) {
                this.cityEnglishName = cityEnglishName;
            }
        }
    }
}
