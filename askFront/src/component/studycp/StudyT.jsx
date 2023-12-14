function SText () {
    return(
        <div className=" h-96 float-left w-4/6 m-10 p-3 ">
            <div>
                <h1 className="font-bold text-4xl">공부 글</h1>
            </div>
            <div className="mt-8">
                <div class="relative overflow-x-auto">
                    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                        <thead class="text-xs text-gray-700 uppercase bg-gray-50 border-b border-t">
                            <tr>
                                <th colspan="3" class="pl-6 py-3">
                                    제목
                                </th>
                                <th scope="col" class="pl-6 py-3">
                                    작성자
                                </th>
                                <th scope="col" class="pl-6 py-3">
                                    작성일
                                </th>
                                <th scope="col" class="pl-6 py-3">
                                    조회
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="3" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <td class="pl-6 py-4">
                                    홍길동
                                </td>
                                <td class="pl-6 py-4">
                                    2023-01-01
                                </td>
                                <td class="pl-6 py-4">
                                    2
                                </td>
                            </tr>
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th colspan="3" class="pl-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    글 제목
                                </th>
                                <td class="pl-6 py-4">
                                    홍길동
                                </td>
                                <td class="pl-6 py-4">
                                    2023-01-01
                                </td>
                                <td class="pl-6 py-4">
                                    2
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

function StudyT() {

    // useState와 useEffect를 활용해서 테이블 내용을 보여주기 위해 사용함.
    return(
        <SText />
    );
}

export default StudyT;
