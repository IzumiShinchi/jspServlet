// inquiry.js

// 문의글 작성 폼 제출 이벤트
document.getElementById("inquiryForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const password = document.getElementById("password").value;

    if (!title || !content || !password) {
        alert("모든 필드를 입력해주세요.");
        return;
    }

    fetch("/inquiry/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ title, content, password })
    }).then(response => {
        if (response.ok) {
            window.location.href = "/inquiry/list";
        } else {
            alert("문의글 작성에 실패했습니다.");
        }
    }).catch(error => console.error("Error:", error));
});

// 문의글 조회 시 비밀번호 확인
function checkPasswordAndLoadInquiry(inquiryId) {
    const inputPassword = prompt("비밀번호를 입력하세요:");

    fetch(`/inquiry/checkPassword`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ inquiryId, inputPassword })
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("비밀번호가 일치하지 않습니다.");
        }
    }).then(data => {
        if (data.valid) {
            window.location.href = `/inquiry/view/${inquiryId}`;
        } else {
            alert("비밀번호가 일치하지 않습니다.");
        }
    }).catch(error => alert(error.message));
}

// 답글 작성 폼 제출 이벤트
document.getElementById("replyForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const commentTitle = document.getElementById("commentTitle").value;
    const commentContent = document.getElementById("commentContent").value;
    const inquiryId = document.getElementById("inquiryId").value;

    if (!commentTitle || !commentContent) {
        alert("모든 필드를 입력해주세요.");
        return;
    }

    fetch("/admin/inquiry/reply", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ inquiryId, commentTitle, commentContent })
    }).then(response => {
        if (response.ok) {
            window.location.href = "/admin/inquiry";
        } else {
            alert("답글 작성에 실패했습니다.");
        }
    }).catch(error => console.error("Error:", error));
});

// 작성자 이름 또는 비밀번호 입력에 따른 문의글 작성 폼 초기화
function initializeInquiryForm(isLoggedIn, userName) {
    if (isLoggedIn) {
        document.getElementById("inquiryName").value = userName;
        document.getElementById("inquiryName").disabled = true;
    } else {
        document.getElementById("inquiryName").disabled = false;
    }
}

// 문의글 리스트 초기화
function initializeInquiryList() {
    const inquiryItems = document.querySelectorAll(".inquiry-item");
    inquiryItems.forEach(item => {
        item.addEventListener("click", function() {
            const inquiryId = this.dataset.inquiryId;
            checkPasswordAndLoadInquiry(inquiryId);
        });
    });
}

// 페이지 로드 시 초기화 함수 호출
document.addEventListener("DOMContentLoaded", function() {
    if (document.getElementById("inquiryListPage")) {
        initializeInquiryList();
    }
    if (document.getElementById("inquiryFormPage")) {
        const isLoggedIn = document.getElementById("isLoggedIn").value === "true";
        const userName = document.getElementById("userName").value;
        initializeInquiryForm(isLoggedIn, userName);
    }
});

// 게시글 검색
$(document).ready(function() {
    // 검색 버튼 클릭 이벤트
    $('#searchButton').on('click', function() {
        searchInquiries();
    });

    // 검색 함수
    function searchInquiries() {
        var keyword = $('#searchKeyword').val();
        var searchType = $('#searchType').val();

        $.ajax({
            url: '/inquiry/search',
            type: 'GET',
            data: {
                keyword: keyword,
                searchType: searchType
            },
            success: function(response) {
                // 검색 결과를 목록에 반영
                updateInquiryList(response);
            },
            error: function() {
                alert('검색 중 오류가 발생했습니다.');
            }
        });
    }

    // 검색 결과를 목록에 업데이트하는 함수
    function updateInquiryList(inquiries) {
        var $inquiryList = $('#inquiryList');
        $inquiryList.empty();

        if (inquiries.length > 0) {
            inquiries.forEach(function(inquiry) {
                var $tr = $('<tr></tr>');
                $tr.append('<td>' + inquiry.inquiryIdx + '</td>');
                $tr.append('<td><a href="#" data-inquiry-id="' + inquiry.inquiryIdx + '" class="inquiry-item">' + inquiry.inquiryTitle + '</a></td>');
                $tr.append('<td>' + inquiry.inquiryName + '</td>');
                $tr.append('<td>' + inquiry.createdDate + '</td>');
                $tr.append('<td>' + (inquiry.commentTitle ? '답글 완료' : '미완료') + '</td>');
                $inquiryList.append($tr);
            });
        } else {
            $inquiryList.append('<tr><td colspan="5" class="text-center">조회된 결과가 없습니다.</td></tr>');
        }
    }
});