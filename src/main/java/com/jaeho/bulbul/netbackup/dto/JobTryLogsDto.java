package com.jaeho.bulbul.netbackup.dto;

import lombok.Data;

/**
 * {
 *   "data": [
 *     {
 *       "type": "parsedTryLogResponse",
 *       "id": "20-1",
 *       "attributes": {
 *         "tryNumber": 1,
 *         "jobId": 20,
 *         "tryStartTimestamp": "2023-06-25T17:22:20.000Z",
 *         "tryEndTimestamp": "2023-06-25T17:22:21.000Z",
 *         "tryStatusCode": 1,
 *         "tryStatusMessage": "the requested operation was partially successful",
 *         "tryKbps": 0,
 *         "tryKbWritten": 0,
 *         "tryFilesWritten": 0,
 *         "trySourceStorageUnit": "",
 *         "tryDestinationStorageUnit": "",
 *         "tryMediaServer": "",
 *         "tryProcessId": 2909,
 *         "trySessionId": 0,
 *         "tryLogEntries": [
 *           {
 *             "message": "bpdbm (pid=2909) image catalog cleanup",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "bpdbm (pid=2909) Cleaning up tables in the relational database",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "bpdbm (pid=2909) Hot catalog backup is not configured for 'nbu10', catalog cleanup will return partial success until hot catalog backup is configured.",
 *             "severity": "WARNING",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "bpdbm (pid=2909) deleting images which expire before Mon Jun 26 02:22:19 2023 (1687713739)",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "nbdelete (pid=5569) deleting expired images. Media Server: nbu10 Media: /root/stu_test",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "bpdm (pid=5579) started",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "started process bpdm (pid=5579)",
 *             "timestamp": "2023-06-25T17:22:20.000Z"
 *           },
 *           {
 *             "message": "bpdm (pid=5579) initial volume /root/stu_test: Kbytes total capacity: 36764120, used space: 15607424, free space: 21156696",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:21.000Z"
 *           },
 *           {
 *             "message": "bpdm (pid=5579) Removed 4552 Kbytes",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:21.000Z"
 *           },
 *           {
 *             "message": "bpdm (pid=5579) ending volume /root/stu_test: Kbytes total capacity: 36764120, used space: 15602872, free space: 21161248",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:21.000Z"
 *           },
 *           {
 *             "message": "bpdm (pid=5579) EXITING with status 0",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:21.000Z"
 *           },
 *           {
 *             "message": "bpdbm (pid=2909) deleted 3 expired records, compressed 0, intelligent catalog archived 0, tir removed 0, deleted 3 expired copies",
 *             "severity": "INFO",
 *             "timestamp": "2023-06-25T17:22:21.000Z"
 *           }
 *         ]
 *       },
 *       "links": {
 *         "self": {
 *           "href": "/admin/jobs/20/try-logs/1"
 *         },
 *         "last": {
 *           "href": "/admin/jobs/20/try-logs/1"
 *         },
 *         "first": {
 *           "href": "/admin/jobs/20/try-logs/1"
 *         }
 *       }
 *     }
 *   ]
 * }
 */
@Data
public class JobTryLogsDto {

    private Data[] data;
    @lombok.Data
    private static class Data {
        private String type;
        private String id;
        private Attributes attributes;
        private Links links;
    }

    @lombok.Data
    private static class Attributes {
        private int tryNumber;
        private int jobId;
        private String tryStartTimestamp;
        private String tryEndTimestamp;
        private int tryStatusCode;
        private String tryStatusMessage;
        private int tryKbps;
        private int tryKbWritten;
        private int tryFilesWritten;
        private String trySourceStorageUnit;
        private String tryDestinationStorageUnit;
        private String tryMediaServer;
        private int tryProcessId;
        private int trySessionId;
        private TryLogEntries[] tryLogEntries;
    }


    @lombok.Data
    private static class TryLogEntries {
        private String message;
        private String severity;
        private String timestamp;
    }


    @lombok.Data
    private static class Links {
        private Self self;
        private Last last;
        private First first;
    }

    @lombok.Data
    private static class Self {
        private String href;
    }

    @lombok.Data
    private static class Last {
        private String href;
    }

    @lombok.Data
    private static class First {
        private String href;
    }

}
