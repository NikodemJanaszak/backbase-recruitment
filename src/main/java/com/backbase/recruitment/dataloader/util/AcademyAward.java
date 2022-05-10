package com.backbase.recruitment.dataloader.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcademyAward {

    private String wonYear;

    private String title;

    private boolean wonAward;
}
