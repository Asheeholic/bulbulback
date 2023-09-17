package com.jaeho.bulbul.netbackup.dto;

import lombok.Builder;
import lombok.Data;

/**
 * {
 *   "data": [
 *     {
 *       "type": "job",
 *       "id": "string",
 *       "attributes": {
 *         "jobId": 0
 *       },
 *       "links": {
 *         "self": {
 *           "href": "string"
 *         }
 *       }
 *     }
 *   ]
 * }
 */
@Data
public class ManualBackupSuccessDto {
    private Data[] data;

    @lombok.Data
    public static class Data {
        private String type;
        private String id;
        private Attributes attributes;
        private Links links;
    }
    @lombok.Data
    public static class Attributes {
        private int jobId;
    }
    @lombok.Data
    public static class Links {
        private Self self;
    }
    @lombok.Data
    public static class Self {
        private String href;
    }

}
