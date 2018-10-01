package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 15,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 15,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 15,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        HashMap<LocalDate, Integer> hm = new HashMap<LocalDate, Integer>();
        HashMap<LocalDate, Integer> sm = new HashMap<LocalDate, Integer>();
        List<UserMealWithExceed> userMealWithExceed = new ArrayList<UserMealWithExceed>();

        //base cycle

        Integer am;
        for (UserMeal i : mealList) {

            am = hm.get(i.getDateTime().toLocalDate());
            hm.put(i.getDateTime().toLocalDate(), am == null ? i.getCalories() : am + i.getCalories());

        }

        for (Map.Entry<LocalDate, Integer> pair : hm.entrySet()) {
            if (pair.getValue()>caloriesPerDay)
                sm.put(pair.getKey(),pair.getValue());

        }

        for (UserMeal i : mealList) {
            for (Map.Entry<LocalDate,Integer> pair : sm.entrySet()) {
                if (i.getDateTime().getHour()>startTime.getHour() && i.getDateTime().getHour()<endTime.getHour()) {
                    if (i.getDateTime().toLocalDate().equals(pair.getKey()))
                        userMealWithExceed.add(new UserMealWithExceed(i.getDateTime(),i.getDescription(),i.getCalories(),true));
                    else
                        userMealWithExceed.add(new UserMealWithExceed(i.getDateTime(),i.getDescription(),i.getCalories(),false));
                }
            }
        }


        //Steam API
        /*
        mealList.stream().map(i->hm.put(i.getDateTime().toLocalDate(), hm.get(i.getDateTime().toLocalDate()) == null ? i.getCalories() : hm.get(i.getDateTime().toLocalDate()) + i.getCalories())).collect(Collectors.toList());
        hm.entrySet().stream().filter(x->x.getValue()>caloriesPerDay).map(x->sm.put(x.getKey(),x.getValue())).collect(Collectors.toList());

        mealList.stream().filter(i->i.getDateTime().getHour()>startTime.getHour() && i.getDateTime().getHour()<endTime.getHour()).map(x->sm.entrySet().stream().filter(i->x.getDateTime().toLocalDate().equals(i.getKey())).map(i->userMealWithExceed.add(new UserMealWithExceed(x.getDateTime(),x.getDescription(),x.getCalories(),true))).collect(Collectors.toList())).collect(Collectors.toList());
        */
        return userMealWithExceed;
    }
}
