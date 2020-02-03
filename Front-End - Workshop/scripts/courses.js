const courses = [
    {'name' : 'Java Fundamentals', 'price' : 170},
    {'name' : 'Java Advanced', 'price' : 180},
    {'name' : 'Java DB', 'price' : 190},
    {'name' : 'Java Web', 'price' : 490},
];

const getCourseItem = (course) =>{
    const input = $('<input/>')
        .attr('type', 'checkbox')
        .val(course.name);
    return $('<label/>').append(input).append(course.name);
};

const generateCourses = (courses) => {
    courses.map(course => getCourseItem(course))
        .map(courseItem => {
            return $('<li>')
                .append(courseItem);
        })
        .forEach(item => item.appendTo('#list-courses'));
};


$(function(){
   generateCourses(courses);
});
