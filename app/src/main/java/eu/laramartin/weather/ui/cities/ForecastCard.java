package eu.laramartin.weather.ui.cities;

import java.util.List;

import eu.laramartin.weather.ui.common.TempFormat;

/**
 * Created by Lara on 19/11/2016.
 */
public class ForecastCard {
    public static class ForecastCardItem {
        private String dayOfTheWeek;
        private int tempMin;
        private int tempMax;
        private int icon;
        private TempFormat tempFormat;

        public String getDayOfTheWeek() {
            return dayOfTheWeek;
        }

        public void setDayOfTheWeek(String dayOfTheWeek) {
            this.dayOfTheWeek = dayOfTheWeek;
        }

        public int getTempMin() {
            return tempMin;
        }

        public void setTempMin(int tempMin) {
            this.tempMin = tempMin;
        }

        public int getTempMax() {
            return tempMax;
        }

        public void setTempMax(int tempMax) {
            this.tempMax = tempMax;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public TempFormat getTempFormat() {
            return tempFormat;
        }

        public void setTempFormat(TempFormat tempFormat) {
            this.tempFormat = tempFormat;
        }

        @Override
        public String toString() {
            return "ForecastCardItem{" +
                    "dayOfTheWeek='" + dayOfTheWeek + '\'' +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    ", icon=" + icon +
                    ", tempFormat=" + tempFormat +
                    '}';
        }
    }

    private List<ForecastCardItem> list;

    public List<ForecastCardItem> getList() {
        return list;
    }

    public void setList(List<ForecastCardItem> list) {
        this.list = list;
    }



}
