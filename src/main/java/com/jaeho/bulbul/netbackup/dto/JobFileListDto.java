package com.jaeho.bulbul.netbackup.dto;

import lombok.Data;

/**
 * {
 *   "data": {
 *     "type": "file-lists",
 *     "id": "11",
 *     "attributes": {
 *       "fileList": [
 *         "/tmp"
 *       ]
 *     },
 *     "links": {
 *       "self": {
 *         "href": "/admin/jobs/11/file-lists"
 *       }
 *     }
 *   }
 * }
 */
@Data
public class JobFileListDto {

    private Data data;

    @lombok.Data
    private static class Data {
        private String type;
        private String id;
        private Attributes attributes;
        private Links links;
    }

    @lombok.Data
    private static class Attributes {
        private String[] fileList;
    }

    @lombok.Data
    private static class Links {
        private Self self;
    }

    @lombok.Data
    private static class Self {
        private String href;
    }

}
