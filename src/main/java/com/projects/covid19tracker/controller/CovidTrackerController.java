package com.projects.covid19tracker.controller;

import com.projects.covid19tracker.model.LatestData;
import com.projects.covid19tracker.service.CovidTrackerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class CovidTrackerController {

    @Autowired
    CovidTrackerDataService covidTrackerDataService;

    @GetMapping("/")
    public String home(Model model) {
        long startTime = System.currentTimeMillis();
        LatestData latestData = covidTrackerDataService.getLatestData();
        String totalCases = NumberFormat.getNumberInstance(Locale.UK).format(Long.valueOf(latestData.getTotalCases()));
        String totalActiveCases = NumberFormat.getNumberInstance(Locale.UK).format(Long.valueOf(latestData.getActiveCases()));
        String deaths = NumberFormat.getNumberInstance(Locale.UK).format(Long.valueOf(latestData.getDeaths()));
        List<LatestData.RegionData> regionWiseData = latestData.getRegionData().stream()
                .sorted(new Comparator<LatestData.RegionData>() {
                    @Override
                    public int compare(LatestData.RegionData o1, LatestData.RegionData o2) {
                        return Integer.parseInt(o2.getRecovered()) - Integer.parseInt(o1.getRecovered());
                    }
                })
                .collect(Collectors.toList());

        String recovered = NumberFormat.getNumberInstance(Locale.US).format(Long.valueOf(latestData.getRecovered()));
        NumberFormat.getNumberInstance(Locale.US).format(Long.valueOf(latestData.getRecovered()));

        List<LatestData.RegionData> topRecoveryStates = regionWiseData.subList(0, 4);

        model.addAttribute("regionWiseData", regionWiseData);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("totalActiveCases", totalActiveCases);
        model.addAttribute("recovered", recovered);
        model.addAttribute("topRecoveryStates", topRecoveryStates);
        model.addAttribute("lastUpdated", (int)((System.currentTimeMillis() - startTime) / 1000 + (Math.random() * 10)));
        return "home";
    }
}
