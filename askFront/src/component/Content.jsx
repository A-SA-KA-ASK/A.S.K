

function Content () {

    // tbody부분에 Section부분에서 받아온 내용을 map을 통하여 내용을 보여주려고 함.

    return(
        <div className=" h-96 float-left w-4/6 m-10 p-3 ">
            <div className="mb-8">
                <div class="relative overflow-x-auto">
                    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                        <thead class="text-xs text-gray-700 uppercase bg-gray-50 border-b">
                            <tr>
                                <th colspan="3" class="pl-6 py-3">
                                    카테고리 일상
                                </th>
                                <th></th>
                                <th scope="col" class="pl-6 py-3 float-right">
                                    + 더보기
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="3" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <th></th>
                                <td class="pl-6 py-4 float-right">
                                    조회 수
                                </td>
                            </tr>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="3" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <th></th>
                                <td class="pl-6 py-4 float-right">
                                    조회 수
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="">
                <div class="relative overflow-x-auto">
                    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                        <thead class="text-xs text-gray-700 uppercase bg-gray-50 border-b">
                            <tr>
                                <th colspan="5" class="pl-6 py-3">
                                    카테고리 공부
                                </th>
                                <th scope="col" class="pl-6 py-3 float-right">
                                    + 더보기
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="5" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <td class="pl-6 py-4 float-right">
                                    조회 수
                                </td>
                            </tr>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="5" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <td class="pl-6 py-4 float-right">
                                    조회 수
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default Content;