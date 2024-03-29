package com.likebookapp.service.impl;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodsEnum;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.service.MoodService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void initMood() {
        if (moodRepository.count() == 0) {

            Arrays.stream(MoodsEnum.values()).forEach(moodsEnum -> {
                Mood mood = new Mood();
                mood.setMoodName(moodsEnum);
                mood.setDescription("");
                this.moodRepository.save(mood);
            });
        }
    }

    @Override
    public Mood findMoodByName(MoodsEnum moodsEnum) {
        return this.moodRepository.findByMoodName(moodsEnum).orElse(null);
    }
}
