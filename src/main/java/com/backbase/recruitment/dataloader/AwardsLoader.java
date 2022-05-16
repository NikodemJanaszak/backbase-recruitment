package com.backbase.recruitment.dataloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AwardsLoader {
    @Value("${dataLoader.category.bestPicture}")
    private String BEST_PICTURE;

    public List<AcademyAward> loadAwards() {
        List<AcademyAward> academyAwards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dataloader/academy_awards.csv"))) {
            String line;

            int firstLine = 0;
            while ((line = reader.readLine()) != null) {
                if (firstLine == 0) {
                    firstLine++;
                    continue;
                }
                String[] fields = line.split(",");

                if (fields[1].equals(BEST_PICTURE)) {
                    academyAwards.add(createAcademyAward(fields));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return academyAwards.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private AcademyAward createAcademyAward(String[] fields) {
        String wonYear = getWonYearFromCSV(fields[0]);
        String title = fields[2];
        Boolean wonAward = getWonAwardFromCSV(fields[fields.length - 1]);
        return new AcademyAward(wonYear, title, wonAward);
    }

    private String getWonYearFromCSV(String field) {
        String[] splitResult = field.split(" ");
        return splitResult[0];
    }

    private Boolean getWonAwardFromCSV(String field) {
        return field.equals("YES");
    }
}
