package ar.edu.unnoba.util;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unnoba.domain.PlayList;
import ar.edu.unnoba.domain.Song;
import ar.edu.unnoba.dto.PlayListDTO;
import ar.edu.unnoba.dto.SongDTO;

public class Adapter {

	public List<SongDTO> domainToDTO(List<Song> myList) {
		List<SongDTO> listResult = new ArrayList<SongDTO>();
			
		for (Song s : myList) {
			SongDTO song = new SongDTO();
			song.setAuthor(s.getAuthor());
			song.setGenre(s.getGenre());
			song.setName(s.getName());
			listResult.add(song);
		}
		return listResult;
	}
	
	public List<PlayListDTO> playlistsCreated(List<PlayList> myList) {
		List<PlayListDTO> listResult = new ArrayList<PlayListDTO>();
			
		for (PlayList ml : myList) {
			PlayListDTO pl = new PlayListDTO();
			pl.setName(ml.getName());
			listResult.add(pl);
		}
		return listResult;
	}
	
	public PlayListDTO getInformationOfPlaylist(PlayList pl) {
		PlayListDTO plDTO = new PlayListDTO();
		plDTO.instanceArraylist();
		plDTO.setName(pl.getName());
		for (Song s : pl.getSongs()) {
			SongDTO sDTO = new SongDTO();
			sDTO.setAuthor(s.getAuthor());
			sDTO.setGenre(s.getGenre());
			sDTO.setName(s.getName());
			plDTO.addSong(sDTO);
		}
		
		return plDTO;
	}
}


