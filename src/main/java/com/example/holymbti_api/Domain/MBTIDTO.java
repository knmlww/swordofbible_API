package com.example.holymbti_api.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MBTIDTO {

    // MBTI_ISSUE 데이터
    private long issueId;
    private int issueNum;
    private String mbtiResult;
    private int iResult;
    private int eResult;
    private int nResult;
    private int sResult;
    private int tResult;
    private int fResult;
    private int jResult;
    private int pResult;
    private String imgName;
    private Date regDt;
    private int mbtiCount;

    // MBTI_BASS 데이터
    private long typeId;
    private String typeName;
    private String typeDtlName;
    private String typeImgUrl;
    private String typePray;
    private String typeCcmName;
    private String typeCcmSinger;
    private String typeCcmUrl;
    private String typeCcmLyric;
    private String typeCcmImgUrl;
    private String typeThumbnailImageUrl;
    private String typeDesc;
}
