package com.example.asterix_api;

import lombok.With;

@With
public record Characters(
        String id,
        String name,
        int age,
        String profession,
        Long createdAt
) {
}
