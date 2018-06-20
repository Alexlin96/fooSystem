package com.pure.service;

import java.util.Map;

import com.pure.common.PageInfo;
import com.pure.db.TFilm;

public interface FilmService {

	PageInfo getFilmPageInfo(Integer pageCurrent, Integer pageSize, TFilm tFilm);

	Map<String, Object> getFilmById(Integer id);

	void deleteFilmById(Integer id);

	void saveFilm(TFilm tFilm);
        
}
