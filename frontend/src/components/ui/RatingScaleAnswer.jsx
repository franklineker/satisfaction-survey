import { Star } from 'lucide-react';


export default function RatingScaleAnswer({ props }) {

    return (
        <div className='flex ml-4'>
            {[...Array(5)].map((_, index) => (
                <Star key={index} className={index !== 4 ? `mx-1 fill-yellow-400` : ''} />
            ))}
        </div>
    )
}
