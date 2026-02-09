package com.example.holymbti_api.Service;

import com.example.holymbti_api.Domain.MbtiIssue;
import com.example.holymbti_api.Domain.MBTIDTO;
import com.example.holymbti_api.Domain.MBTI_BASS;
import com.example.holymbti_api.Repository.APIRepository;
import com.example.holymbti_api.Repository.MbtiBassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class APIService {
    private final APIRepository apiRepository;
    private final MbtiBassRepository mbtiBassRepository;

    public int getResultMember() {
        Long temp = apiRepository.countBy();
        return temp.intValue();
    }

    public MbtiIssue insertResult(@RequestBody MbtiIssue param) {
        return apiRepository.save(param);
    }

    public int findMyMBTICount(@RequestBody String param) {
        Long result = apiRepository.countByMbtiResult(param);
        return result.intValue();
    }

    public MBTIDTO searchResult(long issueId) {
        MbtiIssue issue = apiRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("해당 issueId의 결과를 찾을 수 없습니다: " + issueId));

        MBTI_BASS bass = mbtiBassRepository.findByTypeDtlName(issue.getImgName());

        int mbtiCount = findMyMBTICount(issue.getMbtiResult());

        MBTIDTO dto = new MBTIDTO();
        // MBTI_ISSUE 데이터
        dto.setIssueId(issue.getIssueId());
        dto.setIssueNum(issue.getIssueNum());
        dto.setMbtiResult(issue.getMbtiResult());
        dto.setIResult(issue.getIResult());
        dto.setEResult(issue.getEResult());
        dto.setNResult(issue.getNResult());
        dto.setSResult(issue.getSResult());
        dto.setTResult(issue.getTResult());
        dto.setFResult(issue.getFResult());
        dto.setJResult(issue.getJResult());
        dto.setPResult(issue.getPResult());
        dto.setImgName(issue.getImgName());
        dto.setRegDt(issue.getRegDt());
        dto.setMbtiCount(mbtiCount);

        // MBTI_BASS 데이터
        if (bass != null) {
            dto.setTypeId(bass.getTypeId());
            dto.setTypeName(bass.getTypeName());
            dto.setTypeDtlName(bass.getTypeDtlName());
            dto.setTypeImgUrl(bass.getTypeImgUrl());
            dto.setTypePray(bass.getTypePray());
            dto.setTypeCcmName(bass.getTypeCcmName());
            dto.setTypeCcmSinger(bass.getTypeCcmSinger());
            dto.setTypeCcmUrl(bass.getTypeCcmUrl());
            dto.setTypeCcmLyric(bass.getTypeCcmLyric());
            dto.setTypeCcmImgUrl(bass.getTypeCcmImgUrl());
            dto.setTypeThumbnailImageUrl(bass.getTypeThumbnailImageUrl());
            dto.setTypeDesc(bass.getTypeDesc());
        }

        return dto;
    }
}
