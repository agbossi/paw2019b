const getMaxPage = (link) => {
    let maxPageRef = link.split(",")[1].split(";")
    let max = maxPageRef[0].charAt(maxPageRef[0].length - 2)
    return max
}

export default {
    getMaxPage
}