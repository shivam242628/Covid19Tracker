package com.projects.covid19tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.covid19tracker.model.LatestData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class CovidTrackerDataService {

    private static final String COVID_TRACKER_DATA_URL = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

    private LatestData latestData = new LatestData();

    public LatestData getLatestData() {
        return latestData;
    }

    @PostConstruct
    @Scheduled(fixedDelay = 5000, initialDelay = 3000)
    public void fetchLatestData() throws IOException{
        System.out.println("Fetching data...");

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(COVID_TRACKER_DATA_URL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        this.latestData = mapper.readValue(result, LatestData.class);

        System.out.println("Fetched data for " + this.latestData.getRegionData().size() + " states.");
    }
}
