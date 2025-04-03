package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;
import org.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            //file이 없다면
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        } else {
            boardDTO.setFileAttached(1);
            boardRepository.save(boardDTO);
            BoardDTO savedBoard = boardRepository.save(boardDTO);

            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
                String originalFilename = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
                String savePath = "C:/Temp/uploads/" + storedFileName;

                boardFile.transferTo(new File(savePath));

                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFilename);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());

                boardRepository.saveFile(boardFileDTO);
            }
        }
    }


    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }


    public void delete(long id) {
        boardRepository.delete(id);
    }

    public List<BoardFileDTO> findFile(long id) {
        return boardRepository.findFile(id);
    }
}
