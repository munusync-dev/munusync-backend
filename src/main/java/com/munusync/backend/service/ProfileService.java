package com.munusync.backend.service;

import com.munusync.backend.dto.request.ProfileRequest;
import com.munusync.backend.entity.*;
import com.munusync.backend.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile createProfile(User user, ProfileRequest request) {
        Profile profile = Profile.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .bio(request.getBio())
                .dateOfBirth(request.getDateOfBirth())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        return profileRepository.save(profile);
    }
}
