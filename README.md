## Rest-Api---Car-Review-Application

### Endopoints
- Get All Cars:      /api/car?pageNo={x}&pageSize={y}
- Create Car:        /api/car/create
- Update Car:        /api/car/{carId}/update
- Delete Car:        /api/car/{carId}/delete

#
- Create Review:     /api/car/{carId}/review
- Get All Reviews:   /api/car/{carId}/reviews
- Get Review:        /api/car/{carId}/review/{reviewId}
- Update Review:     /car/{carId}/reviews/{reviewId}
- Delete Review:     /car/{carId}/reviews/{reviewId}

#
Simple security filter chain is active, i can be disabled.
