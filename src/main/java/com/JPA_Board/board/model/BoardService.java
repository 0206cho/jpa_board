package com.JPA_Board.board.model;

import com.JPA_Board.board.entity.Board;
import com.JPA_Board.board.dto.BoardRequestDto;
import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.entity.BoardRepository;
import com.JPA_Board.exception.CustomException;
import com.JPA_Board.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// 아래처럼 클래스 내 final로 선언된 모든 멤버에 대한 생성자를 만들어줌
/*
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
*/
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     * @param params
     * @return 게시글 id
     */
    @Transactional
    // 서비스(Service) 클래스에서 필수적으로 사용
    // 메서드의 실행, 종료, 예외를 기준으로 각각 실행, 종료, 예외를 자동으로 처리
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        // save() 실행 후 entity 객체에는 생성된 게시글 정보가 담기고,
        // 메서드 종료 시 생성된 게시글의 id를 리턴
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 목록
     */
    public List<BoardResponseDto> findAll() {
        // sort객체 : order by id desc, created_date desc 의미
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");

        List<Board> list = boardRepository.findAll(sort);

        // list 변수에 게시글 entity가 담겨있고, 각각의 entity를 BoardResponseDto타입으로 변경(생성)해서 리턴
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());

        /* Stream API를 사용하지 않은 경우 */
        /*
        List<BoardResponseDto> boardList = new ArrayList<>();

        for (Board entity : list) {
            boardList.add(new BoardResponseDto(entity));
        }

        return boardList;
         */
    }

    /**
     * 게시글 리스트 조회 - (삭제 여부 기준)
     * @param deleteYn
     * @return
     */
    public List<BoardResponseDto> findAllByDeleteYn(final char deleteYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     * 해당 메서드 실행 종료되면 update 쿼리가 자동으로 실행 -> Dirty Checking (영속성 컨텍스트에 의해 더티 체킹이 가능해짐)
     * @param id
     * @param params
     * @return 게시글 id
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;

//        Optional 클래스 안 쓸 경우
//        Board entity = boardRepository.findById(id).orElse(null);
//
//        if (entity == null) {
//            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
//        }
//
//        entity.update(params.getTitle(), params.getContent(), params.getWriter());
//        return id;
    }

    /**
     * 게시글 삭제
     * @param id
     * @return 게시글 id
     */
    @Transactional
    public Long delete(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    /**
     * 게시글 상세정보 조회
     * @param id
     * @return 게시글 정보
     */
    @Transactional
    public BoardResponseDto findById (final  Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();
        return new BoardResponseDto(entity);
    }
}
