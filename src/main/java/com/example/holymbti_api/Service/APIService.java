package com.example.holymbti_api.Service;

import com.example.holymbti_api.Domain.MBTI;
import com.example.holymbti_api.Repository.APIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class APIService {
    private final APIRepository apiRepository;
    public int getResultMember(){
        Long temp = apiRepository.countBy();
        return temp.intValue();

    }

    public MBTI insertResult(@RequestBody MBTI param) {
        return apiRepository.save(param);
    }

    public int findMyMBTICount(@RequestBody MBTI paramMBTI) {
        String param = paramMBTI.getMbtiResult();
        Long result = apiRepository.countByMbtiResult(param);
        return result.intValue();
    }
}