export default function getVisiblePageNumbers(currentPage, totalPages) {
    const pages = [];

    if (totalPages <= 7) {
        // If there are few pages, show all of them
        for (let i = 0; i < totalPages; i++) {
            pages.push(i);
        }
        return pages;
    }

    pages.push(0); // Always show the first page

    if (currentPage <= 2) {
        // If the current page is one of the first pages
        pages.push(1, 2, 3);
        pages.push("...");
    } else if (currentPage >= totalPages - 3) {
        // If the current page is one of the last pages
        pages.push("...");
        pages.push(totalPages - 4, totalPages - 3, totalPages - 2);
    } else {
        // If the current page is somewhere in the middle
        pages.push("...");
        pages.push(currentPage - 1, currentPage, currentPage + 1);
        pages.push("...");
    }

    pages.push(totalPages - 1); // Always show the last page

    return pages;
}