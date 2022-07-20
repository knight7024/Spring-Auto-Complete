$(function () {
    $('.ui.search')
        .search({
            minCharacters: 1,
            selectFirstResult: true,
            duration: 200,
            searchDelay: 200,
            apiSettings: {
                onResponse: function (apiResponse) {
                    const response = {
                            results: []
                        }
                    ;
                    $.each(apiResponse.userList, function (index, item) {
                        response.results.push({
                           title: item.firstName + item.lastName,
                        });
                    });
                    return response;
                },
                url: '/api/user/search?name={query}'
            }
        })
    ;
});
