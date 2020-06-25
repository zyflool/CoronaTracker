package com.zyflool.coronatracker.net;

import java.util.List;

public class ProvinceNameResponse {

    /**
     * results : ["上海市","不丹","东帝汶","中国","中非共和国","丹麦","乌克兰","乌兹别克斯坦","乌干达","乌拉圭","乍得","也门共和国","云南省","亚美尼亚","以色列","伊拉克","伊朗","伯利兹","佛得角","俄罗斯","保加利亚","克罗地亚","关岛","内蒙古自治区","冈比亚","冰岛","几内亚","几内亚比绍","列支敦士登","刚果（布）","刚果（金）","利比亚","利比里亚","加拿大","加纳","加蓬","匈牙利","北京市","北爱尔兰","北马其顿","北马里亚纳群岛联邦","南苏丹","南非","博茨瓦纳","卡塔尔","卢旺达","卢森堡","印度","印度尼西亚","危地马拉","厄瓜多尔","厄立特里亚","叙利亚","古巴","台湾","吉尔吉斯斯坦","吉布提","吉林省","哈萨克斯坦","哥伦比亚","哥斯达黎加","喀麦隆","四川省","土库曼斯坦","土耳其","圣其茨和尼维斯","圣卢西亚","圣多美和普林西比","圣巴泰勒米","圣巴泰勒米岛","圣文森特和格林纳丁斯","圣皮埃尔和密克隆群岛","圣马丁岛","圣马力诺","圭亚那","坦桑尼亚","埃及","埃塞俄比亚","塔吉克斯坦","塞内加尔","塞尔维亚","塞拉利昂","塞浦路斯","塞舌尔","墨西哥","多哥","多米尼克","多米尼加","大不列颠及北爱尔兰联合王国","天津市","奥地利","委内瑞拉","孟加拉国","宁夏回族自治区","安哥拉","安圭拉","安徽省","安提瓜和巴布达","安道尔","尼加拉瓜","尼日利亚","尼日尔","尼泊尔","山东省","山西省","巴勒斯坦","巴哈马","巴基斯坦","巴巴多斯","巴布亚新几内亚","巴拉圭","巴拿马","巴林","巴西","布基纳法索","布隆迪共和国","希腊","广东省","广西壮族自治区","库拉索岛","开曼群岛","德国","意大利","拉脱维亚","挪威","捷克","摩尔多瓦","摩洛哥","摩纳哥","文莱","斐济","斯威士兰","斯洛伐克","斯洛文尼亚","斯里兰卡","新加坡","新喀里多尼亚","新疆维吾尔自治区","新西兰","日本","智利","柬埔寨","根西岛","格林那达","格陵兰","格鲁吉亚","梵蒂冈","比利时","毛里塔尼亚","毛里求斯","江苏省","江西省","沙特阿拉伯","河北省","河南省","法国","法属圭亚那","法属波利尼西亚","法罗群岛","波兰","波多黎各","波黑","泰国","泽西岛","津巴布韦","洪都拉斯","浙江省","海南省","海地","湖北省","湖南省","澳大利亚","澳门","爱尔兰","爱沙尼亚","牙买加","特克斯和凯科斯群岛","特立尼达和多巴哥","玻利维亚","瑞典","瑞士","瓜德罗普岛","甘肃省","留尼旺","留尼汪","白俄罗斯","百慕大","直布罗陀","福克兰群岛","福建省","科威特","科摩罗","科特迪瓦","科索沃","秘鲁","突尼斯","立陶宛","索马里","约旦","纳米比亚","缅甸","罗马尼亚","美国","美属维尔京群岛","老挝","肯尼亚","至尊公主邮轮","芬兰","苏丹","苏里南","英国","英国（含北爱尔兰）","英属维尔京群岛","荷兰","荷兰加勒比地区","荷属圣马丁","莫桑比克","莱索托","菲律宾","萨尔瓦多","葡萄牙","蒙古","蒙特塞拉特","西班牙","西藏自治区","贝宁","贵州省","赞比亚共和国","赤道几内亚","越南","辽宁省","重庆市","钻石公主号邮轮","阿塞拜疆","阿富汗","阿尔及利亚","阿尔巴尼亚","阿曼","阿根廷","阿联酋","阿鲁巴","陕西省","青海省","韩国","香港","马尔代夫","马恩岛","马拉维","马提尼克","马来西亚","马约特","马耳他","马达加斯加","马里","黎巴嫩","黑山","黑龙江省"]
     * success : true
     */

    private boolean success;
    private List<String> results;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
