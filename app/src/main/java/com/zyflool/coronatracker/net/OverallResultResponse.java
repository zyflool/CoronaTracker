package com.zyflool.coronatracker.net;

import java.util.List;

public class OverallResultResponse {

    /**
     * results : [{"currentConfirmedCount":461,"currentConfirmedIncr":20,"confirmedCount":85099,"confirmedIncr":29,"suspectedCount":1888,"suspectedIncr":3,"curedCount":79991,"curedIncr":8,"deadCount":4647,"deadIncr":1,"seriousCount":100,"seriousIncr":1,"globalStatistics":{"currentConfirmedCount":4367712,"confirmedCount":9227705,"curedCount":4382904,"deadCount":477089,"currentConfirmedIncr":9146,"confirmedIncr":20782,"curedIncr":11103,"deadIncr":533},"generalRemark":"1. 3 月 12 日国家卫健委确诊补订遗漏 12 例确诊病例（非 12 日新增），暂无具体省份信息。 2. 浙江省 12 例外省治愈暂无具体省份信息。","remark1":"易感人群：人群普遍易感。老年人及有基础疾病者感染后病情较重，儿童及婴幼儿也有发病","remark2":"潜伏期：一般为 3～7 天，最长不超过 14 天，潜伏期内可能存在传染性，其中无症状病例传染性非常罕见","remark3":"宿主：野生动物，可能为中华菊头蝠","remark4":"","remark5":"","note1":"病毒：SARS-CoV-2，其导致疾病命名 COVID-19","note2":"传染源：新冠肺炎的患者。无症状感染者也可能成为传染源。","note3":"传播途径：经呼吸道飞沫、接触传播是主要的传播途径。气溶胶传播和消化道等传播途径尚待明确。","updateTime":1592970980020}]
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
         * currentConfirmedCount : 461
         * currentConfirmedIncr : 20
         * confirmedCount : 85099
         * confirmedIncr : 29
         * suspectedCount : 1888
         * suspectedIncr : 3
         * curedCount : 79991
         * curedIncr : 8
         * deadCount : 4647
         * deadIncr : 1
         * seriousCount : 100
         * seriousIncr : 1
         * globalStatistics : {"currentConfirmedCount":4367712,"confirmedCount":9227705,"curedCount":4382904,"deadCount":477089,"currentConfirmedIncr":9146,"confirmedIncr":20782,"curedIncr":11103,"deadIncr":533}
         * generalRemark : 1. 3 月 12 日国家卫健委确诊补订遗漏 12 例确诊病例（非 12 日新增），暂无具体省份信息。 2. 浙江省 12 例外省治愈暂无具体省份信息。
         * remark1 : 易感人群：人群普遍易感。老年人及有基础疾病者感染后病情较重，儿童及婴幼儿也有发病
         * remark2 : 潜伏期：一般为 3～7 天，最长不超过 14 天，潜伏期内可能存在传染性，其中无症状病例传染性非常罕见
         * remark3 : 宿主：野生动物，可能为中华菊头蝠
         * remark4 :
         * remark5 :
         * note1 : 病毒：SARS-CoV-2，其导致疾病命名 COVID-19
         * note2 : 传染源：新冠肺炎的患者。无症状感染者也可能成为传染源。
         * note3 : 传播途径：经呼吸道飞沫、接触传播是主要的传播途径。气溶胶传播和消化道等传播途径尚待明确。
         * updateTime : 1592970980020
         */

        private int currentConfirmedCount;  //现存确诊人数  = confirmedCount - curedCount - deadCount
        private int currentConfirmedIncr;   //现存确诊人数较昨日增加数量 = confirmedIncr - curedIncr - deadIncr
        private int confirmedCount;     //累计确诊人数
        private int confirmedIncr;      //累计确诊人数较昨日增加数量
        private int suspectedCount;    //疑似感染人数
        private int suspectedIncr;      //疑似感染人数较昨日增加数量
        private int curedCount;       //治愈人数
        private int curedIncr;     //治愈人数较昨日增加数量
        private int deadCount;     //死亡人数
        private int deadIncr;        //死亡人数较昨日增加数量
        private int seriousCount;
        private int seriousIncr;
        private GlobalStatisticsBean globalStatistics;
        private String generalRemark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String note1;
        private String note2;
        private String note3;
        private long updateTime;

        public int getCurrentConfirmedCount() {
            return currentConfirmedCount;
        }

        public void setCurrentConfirmedCount(int currentConfirmedCount) {
            this.currentConfirmedCount = currentConfirmedCount;
        }

        public int getCurrentConfirmedIncr() {
            return currentConfirmedIncr;
        }

        public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
            this.currentConfirmedIncr = currentConfirmedIncr;
        }

        public int getConfirmedCount() {
            return confirmedCount;
        }

        public void setConfirmedCount(int confirmedCount) {
            this.confirmedCount = confirmedCount;
        }

        public int getConfirmedIncr() {
            return confirmedIncr;
        }

        public void setConfirmedIncr(int confirmedIncr) {
            this.confirmedIncr = confirmedIncr;
        }

        public int getSuspectedCount() {
            return suspectedCount;
        }

        public void setSuspectedCount(int suspectedCount) {
            this.suspectedCount = suspectedCount;
        }

        public int getSuspectedIncr() {
            return suspectedIncr;
        }

        public void setSuspectedIncr(int suspectedIncr) {
            this.suspectedIncr = suspectedIncr;
        }

        public int getCuredCount() {
            return curedCount;
        }

        public void setCuredCount(int curedCount) {
            this.curedCount = curedCount;
        }

        public int getCuredIncr() {
            return curedIncr;
        }

        public void setCuredIncr(int curedIncr) {
            this.curedIncr = curedIncr;
        }

        public int getDeadCount() {
            return deadCount;
        }

        public void setDeadCount(int deadCount) {
            this.deadCount = deadCount;
        }

        public int getDeadIncr() {
            return deadIncr;
        }

        public void setDeadIncr(int deadIncr) {
            this.deadIncr = deadIncr;
        }

        public int getSeriousCount() {
            return seriousCount;
        }

        public void setSeriousCount(int seriousCount) {
            this.seriousCount = seriousCount;
        }

        public int getSeriousIncr() {
            return seriousIncr;
        }

        public void setSeriousIncr(int seriousIncr) {
            this.seriousIncr = seriousIncr;
        }

        public GlobalStatisticsBean getGlobalStatistics() {
            return globalStatistics;
        }

        public void setGlobalStatistics(GlobalStatisticsBean globalStatistics) {
            this.globalStatistics = globalStatistics;
        }

        public String getGeneralRemark() {
            return generalRemark;
        }

        public void setGeneralRemark(String generalRemark) {
            this.generalRemark = generalRemark;
        }

        public String getRemark1() {
            return remark1;
        }

        public void setRemark1(String remark1) {
            this.remark1 = remark1;
        }

        public String getRemark2() {
            return remark2;
        }

        public void setRemark2(String remark2) {
            this.remark2 = remark2;
        }

        public String getRemark3() {
            return remark3;
        }

        public void setRemark3(String remark3) {
            this.remark3 = remark3;
        }

        public String getRemark4() {
            return remark4;
        }

        public void setRemark4(String remark4) {
            this.remark4 = remark4;
        }

        public String getRemark5() {
            return remark5;
        }

        public void setRemark5(String remark5) {
            this.remark5 = remark5;
        }

        public String getNote1() {
            return note1;
        }

        public void setNote1(String note1) {
            this.note1 = note1;
        }

        public String getNote2() {
            return note2;
        }

        public void setNote2(String note2) {
            this.note2 = note2;
        }

        public String getNote3() {
            return note3;
        }

        public void setNote3(String note3) {
            this.note3 = note3;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public static class GlobalStatisticsBean {
            /**
             * currentConfirmedCount : 4367712
             * confirmedCount : 9227705
             * curedCount : 4382904
             * deadCount : 477089
             * currentConfirmedIncr : 9146
             * confirmedIncr : 20782
             * curedIncr : 11103
             * deadIncr : 533
             */

            private int currentConfirmedCount;   //现存确诊人数
            private int confirmedCount;      //累计确诊人数
            private int curedCount;        //累计治愈人数
            private int deadCount;        //累计死亡人数
            private int currentConfirmedIncr;    //现存确诊人数较昨日增加量
            private int confirmedIncr;            //累计确诊人数较昨日增加量
            private int curedIncr;              //累计治愈人数较昨日增加量
            private int deadIncr;              //累计死亡人数较昨日增加量

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

            public int getCurrentConfirmedIncr() {
                return currentConfirmedIncr;
            }

            public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
                this.currentConfirmedIncr = currentConfirmedIncr;
            }

            public int getConfirmedIncr() {
                return confirmedIncr;
            }

            public void setConfirmedIncr(int confirmedIncr) {
                this.confirmedIncr = confirmedIncr;
            }

            public int getCuredIncr() {
                return curedIncr;
            }

            public void setCuredIncr(int curedIncr) {
                this.curedIncr = curedIncr;
            }

            public int getDeadIncr() {
                return deadIncr;
            }

            public void setDeadIncr(int deadIncr) {
                this.deadIncr = deadIncr;
            }
        }
    }
}
