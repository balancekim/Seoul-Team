/**
 * 
 */

$(function(){
	
	localChangeProcess();//페이지 들어올 시 서울시 전체 띄어준다.
	kakaomapProcess(addr,companyName);//카카오맵
});
var addr = '서울시 노원구 상계로 12길';
var companyName = '업체명을 클릭해주세요해당장소로 이동합니다';
var data = []; // 받아온 데이터를 저장할 배열 변수
var currentPage = 1; // 현재 페이지 변수 추가
var itemsPerPage = 10; // 페이지당 아이템 수
var totalPage= 0; // 전체 페이지 수
var itemsPerPagination = 10; // 한 페이지네이션에 표시할 아이템 수


function localChangeProcess(){
	var keyWord =$("select[name=local] option:selected").text();
	
	//alert(keyWord)
	
	
	$.ajax({
		url: "/mapPage2",
		type: 'post',
		dataType:'JSON',
		data: {
			keyWord : keyWord,
			
		},
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success : function(response) { // 결과 성공 콜백함수
    		 // AJAX 요청이 성공했을 때 실행되는 콜백 함수입니다
            
            data = response; // 받아온 데이터를 변수에 저장
			
			currentPage = 1;
			calculateTotalPages();
            // 페이징 처리를 위한 함수 호출
            paginateData();

            // 첫 번째 페이지의 데이터를 보여줌
            showPage(currentPage);
        },
        error: function(error) {
            // AJAX 요청이 실패했을 때 실행되는 콜백 함수입니다
            console.log('AJAX request failed:', error);
        }
    });
}
function localChangeAndSearchProcess(){
	var keyWord =$("select[name=local] option:selected").text();
	var searchValue = $("#searchbox").val();
	//alert(keyWord)
	
	
	$.ajax({
		url: "/mapPageSearch2",
		type: 'post',
		dataType:'JSON',
		data: {
			keyWord : keyWord,
			searchValue: searchValue
		},
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success : function(response) { // 결과 성공 콜백함수
    		 // AJAX 요청이 성공했을 때 실행되는 콜백 함수입니다
            
            data = response; // 받아온 데이터를 변수에 저장
			
			currentPage = 1;
			calculateTotalPages();
            // 페이징 처리를 위한 함수 호출
            paginateData();

            // 첫 번째 페이지의 데이터를 보여줌
            showPage(currentPage);
        },
        error: function(error) {
            // AJAX 요청이 실패했을 때 실행되는 콜백 함수입니다
            console.log('AJAX request failed:', error);
        }
    });
}

function calculateTotalPages() {
    totalPage = Math.ceil(data.length / itemsPerPage);
}

// 데이터를 페이징하여 표시하는 함수
function paginateData() {
    
    // 페이지 숫자 생성
    var paginationHtml = '';
    
    var startPage = (Math.ceil(currentPage / itemsPerPagination) - 1) * itemsPerPagination + 1;
    var endPage = startPage + itemsPerPagination - 1;
    if (endPage > totalPage) {
        endPage = totalPage;
    }
    if (startPage>11) {
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + (1) + ')"><<</a>';
    } 
     // 이전 버튼
    if (startPage>1) {
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + (startPage - 1) + ')"><</a>';
    }

    // 페이지 숫자
    for (var i = startPage; i <= endPage; i++) {
        if(i==currentPage){
			
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + i + ')"style="background-color: #a6a7a985">' + i + '</a>';
		}else{
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + i + ')">' + i + '</a>';
    	}
    }

    // 다음 버튼
    if (endPage<totalPage) {
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + (endPage + 1) + ')">></a>';
    }
    if (endPage<totalPage-10) {
        paginationHtml += '<a href="javascript:void(0)" onclick="showPage(' + (totalPage) + ')">>></a>';
    }
    // 페이지 숫자를 pagination 요소에 추가
    $('#pagination').html(paginationHtml);
}

// 해당 페이지의 데이터를 표시하는 함수
function showPage(page) {
   
    currentPage = page; // 페이지당 아이템 수
    var startIndex = (page - 1) * itemsPerPage;
    var endIndex = startIndex + itemsPerPage;

    // 페이지에 해당하는 데이터만 선택하여 표시
    var items = data.slice(startIndex, endIndex);

    // items 데이터를 기반으로 HTML을 생성합니다
    var htmlContent = '';
    items.forEach(function(item) {
        htmlContent += '<tr>';
	    htmlContent += '<td>' + item.ATDRC_NM + '</td>';//지역
	    htmlContent += '<td>';
	    htmlContent += '<a href="javascript:void(0)" onclick="moveMap(\'' + item.BASS_ADRES + '\', \'' + item.CLTUR_EVENT_ETC_NM + '\')">' + item.CLTUR_EVENT_ETC_NM +'</a>';
	    htmlContent += '</td>';
	    htmlContent += '<td>' + item.BASS_ADRES + '</td>';//주소
	    htmlContent += '<td>';
	    htmlContent += '<a href=\'' + item.GUIDANCE_URL + '\' target="_blank">' + item.GUIDANCE_URL +'</a>';
	    htmlContent += '</td>';
	    htmlContent += '</tr>';
    });

    // 생성한 HTML을 특정 위치에 추가합니다
    $('#show').html(htmlContent);

    // 페이지네이션 업데이트
    paginateData()
}
function moveMap(addr, companyName) {
    // addr 값 사용
    //console.log(addr);
    //console.log(companyName);
    // 이후 원하는 작업 수행
    kakaomapProcess(addr,companyName)
}


///////////////////////////////////////////카카오 맵 구성////////////////////////////
function kakaomapProcess(addr,companyName){
	var container = document.getElementById('map');
	var roadviewContainer = document.getElementById('road-map'); //로드뷰를 표시할 div
	
	var options = {
		center: new kakao.maps.LatLng(37.657088, 127.0622366),
		level: 3
	};
	var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
	var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

	

	
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(container, options); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(addr, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
    if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;" >'+companyName+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
		roadviewClient.getNearestPanoId(coords, 50, function(panoId) {
   		roadview.setPanoId(panoId, coords); //panoId와 중심좌표를 통해 로드뷰 실행
	});
        
    } 
});    
}
function changeRoad(){
	$('#road-map-list').show();
	$('#map-list').hide();
	
}
function changeMap(){
	$('#map-list').show();
	$('#road-map-list').hide();
	
}

