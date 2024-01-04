package com.example.askBackend.Board.service;

import com.example.askBackend.Board.entity.Board;
import com.example.askBackend.Board.repository.BoardRepository;
import com.example.askBackend.Category.entity.Category;
import com.example.askBackend.Category.repository.CategoryRepository;
import com.example.askBackend.Exception.AppException;
import com.example.askBackend.Exception.ErrorCode;
import com.example.askBackend.Member.entity.Auth;
import com.example.askBackend.Member.entity.Member;
import com.example.askBackend.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ResponseEntity<String> saveBoard(String title, String content, String categoryName, String id, String role) {

        // 카테고리 찾기
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CATEGORY, "카테고리 존재하지않음"));

        // 작성자 권한 확인
        if(hasPermission(category, role)) {
            throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS, "게시글 작성 권한 존재하지않음.");
        }

        // 작성자 찾기
        Member writer = memberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER_ID, "사용자가 존재하지않음"));

        // 게시글 저장
        Board board = Board.createBoard(writer, title, content, category);
        boardRepository.save(board);

        return ResponseEntity.ok().body("게시글 저장 완료"+ board.getId());
    }

    @Override
    @Transactional
    public ResponseEntity<String> modifyBoard(Long boardNum, String title, String content, String categoryName, String id, String role) {

        // 카테고리 찾기
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_CATEGORY, "카테고리 존재하지않음"));

        // 작성자 권한 확인
        if(hasPermission(category, role)) {
            throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS, "게시글 작성 권한 존재하지않음.");
        }

        Board board = boardRepository.findBoardById(boardNum).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_BOARD, "게시글을 찾을 수 없음"));

        // 게시글 작성자 맞는지 확인
        if(!board.getWriter().getId().equals(id)){
            throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS, "게시글 수정 권한이 존재하지 않음.");
        }

        board.changeBoard(title, content);

        boardRepository.save(board);

        return ResponseEntity.ok().body("게시글 수정 완료");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteBoard(Long boardNum, String name) {
        Board board = boardRepository.findBoardById(boardNum).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_BOARD, "게시글을 찾을 수 없음"));

        if(!board.getWriter().getId().equals(name)){
            throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS, "게시글 삭제 권한이 존재하지 않음.");
        }

        boardRepository.deleteById(boardNum);

        return ResponseEntity.ok().body("게시글 삭제 완료");
    }

    private boolean hasPermission(Category category, String auth){
        List<Auth> authorities = category.getAuthorities();
        return !authorities.contains(Auth.valueOf(auth));
    }
}
