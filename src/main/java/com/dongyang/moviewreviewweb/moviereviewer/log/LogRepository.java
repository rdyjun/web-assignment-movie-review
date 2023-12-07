package com.dongyang.moviewreviewweb.moviereviewer.log;

import java.util.List;

public interface LogRepository {
    boolean create(Log log);

    List<Log> getAllLog();
}
