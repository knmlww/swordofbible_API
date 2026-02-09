package com.example.holymbti_api.Controller;


import com.example.holymbti_api.Domain.MbtiIssue;
import com.example.holymbti_api.Domain.MBTIDTO;
import com.example.holymbti_api.Service.APIService;
import com.example.holymbti_api.Service.AwsS3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;


@RestController
@RequiredArgsConstructor
public class APIcontroller {

    @Autowired
    APIService apiService;

    private final AwsS3Util awsS3Util;


    @PostMapping(value="/holymbti/test")
    public String getTestValue(){
        return "SERVER OK";
    }

    @PostMapping(value="/holymbti/getResultMember")
    public int getResultMember(){
        return apiService.getResultMember();
    }

    @PostMapping(value="/holymbti/insertResult")
    public MbtiIssue insertResult(@RequestBody MbtiIssue paramMBTI){
        return apiService.insertResult(paramMBTI);
    }

    @PostMapping(value="/holymbti/findMyMBTICount")
    public int findMyMBTICount(@RequestBody MBTIDTO paramMBTI){
        return apiService.findMyMBTICount(paramMBTI.getMbtiResult());
    }

    @GetMapping(value="/holymbti/searchResult/{issueId}")
    public MBTIDTO searchResult(@PathVariable("issueId") long issueId){
        return apiService.searchResult(issueId);
    }

    // 다운로드
    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "image") String image) {

        //  ex. image=https://board-example.s3.ap-northeast-2.amazonaws.com/2b8359b2-de59-4765-8da0-51f5d4e556c3.jpg;
        byte[] data = awsS3Util.downloadFile(image);
        ByteArrayResource resource = new ByteArrayResource(data);
        String filename = "말씀의 검.jpg";
        String encodedFilename = UriUtils.encode(filename, StandardCharsets.UTF_8);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + encodedFilename + "\"")
                .body(resource);
    }

}
