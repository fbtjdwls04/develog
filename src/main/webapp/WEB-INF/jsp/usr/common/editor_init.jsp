<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	function submitForm(e) {

		if(e.boardId.value == 0){
			alert('게시판을 선택해주세요');
			e.boardId.focus();
			return;
		}
		
		if(e.title.value.trim().length == 0){
			alert('제목을 입력해주세요');
			e.title.focus();
			return;
		}

		if(e.title.value.trim().length > 100){
			alert('제목은 100자 이내로 작성해주세요 (현재 ' + e.title.value.trim().length + '자)');
			e.title.focus();
			return;
		}
	 	
		if(editor.getMarkdown() == 0){
			alert('내용을 입력해주세요');
			editor.focus();
			return;
		}
		
	 	e.body.value = editor.getHTML();
		
		e.submit();
	}

	const editor = new Editor({
	    el: document.querySelector('#editor'),
	    previewStyle: 'vertical',
	    height: '800px',
	    initialEditType : 'wysiwyg'
	});
</script>